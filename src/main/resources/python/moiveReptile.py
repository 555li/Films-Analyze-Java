import re
import pandas as ps
import requests
import jsonpath
import random
import time
from pymysql import *
from sqlalchemy import create_engine
from bs4 import BeautifulSoup
import json

engine = create_engine('mysql+pymysql://root:root@localhost:3306/dbm')
#定义初始化函数
def init():
    conn = connect(host='localhost',user='root',password='root',database='dbm',port=3306,charset='utf8mb4')
    sql = '''
        create table movies(
            id int primary key auto_increment,
            directors varchar(255),
            rate varchar(255),
            title varchar(255),
            casts varchar(255),
            cover varchar(255),
            year varchar(255),
            types varchar(255),
            country varchar(255),
            lang varchar(255),
            time varchar(255),
            movieTime varchar(255),
            comment_len varchar(255),
            starts varchar(255),
            summary varchar(2555),
            comments text,
            imgList varchar(2555),
            movieUrl varchar(255),
            detailLink varchar(255)
        )
    '''
    cursor = conn.cursor()
    cursor.execute(sql)
    conn.commit()
'保存爬取的电影数据到movieDatas.csv'
def save_to_csv(movieData):
    movieData.to_csv('D:\\电影数据可视化分析系统JAVA\\电影数据可视化分析\\src\\main\\resources\\python\\movieDatas.csv')

'读取movieDatas.csv中爬取的电影数据，存储到数据库中'
def save_to_sql():
    movieData= ps.read_csv('D:\\电影数据可视化分析系统JAVA\\电影数据可视化分析\\src\\main\\resources\\python\\movieDatas.csv',index_col=0)
    movieData.to_sql('movies',con=engine,index=False,if_exists ='append')

'爬虫主要代码'
def spider(reptileTarget,start):
    # 每次调用spider获取20条数据
    headers = {
        'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.3',
        'Referer':'https://movie.douban.com/tag/',
        'Cookie':'bid=MKDEX4REE3M; ll="118159"; _pk_id.100001.4cf6=7108ce55ce75dbb1.1682303583.; __utma=30149280.380447765.1682303584.1682303584.1682303584.1; __utmz=30149280.1682303584.1.1.utmcsr=(direct)|utmccn=(direct)|utmcmd=(none); __utma=223695111.1347919739.1682303584.1682303584.1682303584.1; __utmz=223695111.1682303584.1.1.utmcsr=(direct)|utmccn=(direct)|utmcmd=(none); __yadk_uid=w4hc6wtOU9NYb8u6HAd2d1uHlVWFNf8Y; _vwo_uuid_v2=D02828698466F23B5D842E65675AA47A7|88cfb70487a4198ff15a06ebf710591a; dbcl2="270070109:yRwZCY+W/tU"; ck=7UH6' }
    params = {
        'start':start
    }
    try:

        movieAllResult = requests.get(reptileTarget, params=params, headers=headers)
        movieAllResult = movieAllResult.json()

        detailUrls = jsonpath.jsonpath(movieAllResult, '$.data..url')
        moveisInfomation = jsonpath.jsonpath(movieAllResult, '$.data')[0]
    except requests.exceptions.RequestException as e:
        print("Error: {}".format(e))
    except requests.exceptions.JSONDecodeError as e:
        print("Error decoding JSON: {}".format(e))


    for i,moveInfomation in enumerate(moveisInfomation):
        try:
            resultData = {}
            # 详情
            resultData['detailLink'] = detailUrls[i]
            # 导演（数组）
            resultData['directors'] = ','.join(moveInfomation['directors'])

            # 评分
            resultData['rate'] = moveInfomation['rate']

            # 影片名
            resultData['title'] = moveInfomation['title']

            # 主演（数组）
            resultData['casts'] = ','.join(moveInfomation['casts'])

            # 封面
            resultData['cover'] = moveInfomation['cover']


            # =================进入详情页====================
            """
            r=requests.get(url,params,**kwargs)
            参数解释：
                url: 需要爬取的网站地址。
                params: url中的额外参数，字典或者字节流格式，是可选参数。
                **kwargs : 12个控制访问的参数
            """
            detailMovieRes = requests.get(detailUrls[i], headers=headers)
            bSoup = BeautifulSoup(detailMovieRes.text, 'lxml')

            # 上映年份
            resultData['year'] = re.findall(r'[(](.*?)[)]',bSoup.find('span', class_='year').get_text())[0]

            types = bSoup.find_all('span',property='v:genre')
            for i,span in enumerate(types):
                types[i] = span.get_text()
            # 影片类型（数组）
            resultData['types'] = ','.join(types)
            country = bSoup.find_all('span',class_='pl')[4].next_sibling.strip().split(sep='/')
            for i,c in enumerate(country):
                country[i] = c.strip()
            # 制作国家（数组）
            resultData['country'] = ','.join(country)
            lang = bSoup.find_all('span', class_='pl')[5].next_sibling.strip().split(sep='/')
            for i, l in enumerate(lang):
                lang[i] = l.strip()
            # 影片语言（数组）
            resultData['lang'] = ','.join(lang)

            upTimes = bSoup.find_all('span',property='v:initialReleaseDate')
            upTimesStr = ''
            for i in upTimes:
                upTimesStr = upTimesStr + i.get_text()
            upTime = re.findall(r'\d*-\d*-\d*',upTimesStr)[0]
            # 上映时间
            resultData['time'] = upTime
            if bSoup.find('span',property='v:runtime'):
                # 时间长度
                resultData['movieTime'] = re.findall(r'\d+',bSoup.find('span',property='v:runtime').get_text())[0]
            else:
                # 时间长度
                resultData['movieTime'] = random.randint(39,61)
            # 评论个数
            resultData['comment_len'] = bSoup.find('span',property='v:votes').get_text()
            starts = []
            startAll = bSoup.find_all('span',class_='rating_per')
            for i in startAll:
                starts.append(i.get_text())
            # 星星比例（数组）
            resultData['starts'] = ','.join(starts)
            # 影片简介
            resultData['summary'] = bSoup.find('span',property='v:summary').get_text().strip()

            # 五条热评
            comments_info = bSoup.find_all('span', class_='comment-info')
            comments = [{} for x in range(5)]
            for i, comment in enumerate(comments_info):
                comments[i]['user'] = comment.contents[1].get_text()
                comments[i]['start'] = re.findall('(\d*)', comment.contents[5].attrs['class'][0])[7]
                comments[i]['time'] = comment.contents[7].attrs['title']
            contents = bSoup.find_all('span', class_='short')
            for i in range(5):
                comments[i]['content'] = contents[i].get_text()
            resultData['comments'] = json.dumps(comments)

            # 五张详情图
            imgList = []
            lis = bSoup.select('.related-pic-bd img')
            for i in lis:
                imgList.append(i['src'])
            resultData['imgList'] = ','.join(imgList)
            # =================详情页结束===================

            # =================进入电影页===================
            if bSoup.find('a',class_='related-pic-video'):
                movieUrl = bSoup.find('a', class_='related-pic-video').attrs['href']
                foreshowMovieRes = requests.get(movieUrl,headers=headers)
                foreshowMovieSoup = BeautifulSoup(foreshowMovieRes.text,'lxml')
                movieSrc = foreshowMovieSoup.find('source').attrs['src']
                # 电影路径
                resultData['movieUrl'] = movieSrc
            else:
                resultData['movieUrl'] = '0'


            # =================进入电影页结束===================
            result.append(resultData)
            print('已经爬取%d条数据' % len(result))
        except :
            print("终止")
            return


def main():
    global result
    result = []
    with open('D:\\电影数据可视化分析系统JAVA\\电影数据可视化分析\\src\\main\\resources\\python\\pageNum.txt','r') as fr:
        page = int(fr.readlines()[-1])
        print('开始爬取第%s个20' % page)
        spider(reptileTarget, page * 20)

        time.sleep(5)
        movieData = ps.DataFrame(result)#DataFrame用于创建数据框
        save_to_csv(movieData)
        print('导入csv成功......')
        time.sleep(5)
        save_to_sql()
        print('导入sql成功......')
        with open('./pageNum.txt','a') as fa:
            fa.write(str(page + 1) + '\n')
        result = []
        for i in range(len(result)):
            result.pop()
    main()

if __name__ == '__main__':
    # init()
    print('爬虫开始,耐心等待...')
    reptileTarget = 'https://movie.douban.com/j/new_search_subjects?'
    main()




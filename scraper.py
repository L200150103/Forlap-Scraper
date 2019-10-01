from bs4 import BeautifulSoup as bs
import urllib3
from tabulate import tabulate
import mysql.connector

http = urllib3.PoolManager()
url10 = 'https://forlap.ristekdikti.go.id/perguruantinggi/detail/NkQxMjQxNDItRTc5OC00RjYyLTg3NEItQ0U0MzVCNTQwOUYx'
req = http.request('GET',url10)
urllib3.disable_warnings()
mydb = mysql.connector.connect(
	host = 'localhost',
	user = 'root',
	password = '',
	database = 'db_forlap'
	)
mycursor = mydb.cursor()

if req.status==200:
	soup = bs(req.data,'html.parser')
	table10 = soup.select('table')[2].select('tr')
	print(table10)
	for i in range(2,len(table10)):	
		data10 = table10[i].select('td')[0].text
		link10 = table10[i].select('td')[2].find('a').get('href')
		data11 = table10[i].select('td')[4].text
		data12 = table10[i].select('td')[2].text
		data13 = table10[i].select('td')[3].text
		data14 = table10[i].select('td')[6].text
		print (data10,' - ',link10,' - ',data13,' - ',data11,' - ',data12.replace(' ','_').lower(), '-', data14)
		data12 = data12.replace(' ','_').lower()+'_'+data11.lower()
	
		try:
			mycursor.execute('CREATE TABLE '+data12+' (nim VARCHAR(10), link VARCHAR(255), primary key (nim))')
		except:
			print ("Table "+data12+" is already exist")
		
		http = urllib3.PoolManager()
		url20 = link10
		full = [['\tno'],['\tnim'],['\tnama'],['\tlink']]
		req = http.request('GET',url20)
		urllib3.disable_warnings()
		if req.status==200:
			soup2 = bs(req.data,'html.parser')

			table20 = soup2.select('table')[2].select('tr')
			for i in range(1,len(table20)-1):
				#data20 = soup2.select('table')[2].select('tr')[i].select('td')[0].text
				link20 = soup2.select('table')[2].select('tr')[i].select('td')[2].find('a').get('href')
				data21 = soup2.select('table')[2].select('tr')[i].select('td')[1].text
				print (' - ',link20,' - ',data21)

				http = urllib3.PoolManager() 	
				url = link20
				full = [['\tno'],['\tnim'],['\tnama'],['\tlink']]
				page = 0
				urllib3.disable_warnings()

				for pagex in range(41+1):
					urls = url+'/'+str(page)
					req = http.request('GET',urls)
					
					if req.status == 200:
						soup = bs(req.data,'html.parser')
						table = soup.select('table')[0]

						link = []
						data = []

						for i in range(len(table.select('tr'))):
							data.append([])
						for i in range(len(table.select('tr'))):
							link.append([])
						for th in range(len(table.select('tr')[0].select('th'))):
							data[0].append(table.select('tr')[0].select('th')[th].text)
						for row in range(1,len(table.select('tr'))):
							for isi in range(len(table.select('tr')[1].select('td'))):
								link[row].append(table.select('tr')[row].find('a').get('href'))
								data[row].append(table.select('tr')[row].select('td')[isi].text.strip())

						data[0].append('Link')
						for i in range (1,len(data)):
							data[i].append(link[i][0])
						for i in range(1,len(data)):
							full.append(data[i])
					page = page+20

					link.pop()
					for x in range(1, len(data)):
						print(data[x][1] + ' - ' + data[x][3] + ' - ' + data[x][2])

						#mycursor.execute("select * from "+data12+" where nim="+data[x][1]+" ")
						# if (not is_exist(str(data12),str(data[x][1]))):
						try:
							mycursor.execute('INSERT INTO '+data12+' (nim, link) VALUES ("'+data[x][1]+'", "'+data[x][3]+'")')
							mydb.commit()
						except:
							print ("primary key is exist")




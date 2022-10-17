import mysql.connector

# The following codes are written with the assumption that no SQL keyword will be passed as argument
# Also, it is assumed that no malicious use, such as SQL injection, will occur

def initialize(url, uid, pw):
    # Create database if not exists
    try:
        cnx = mysql.connector.connect(user=uid, password=pw, host=url)
        cur = cnx.cursor()
        query = "CREATE DATABASE IF NOT EXISTS db"
        cur.execute(query)
        cur.close()
    except mysql.connector.Error as err:
        # TO DO: error handling
        print(err)
    finally:
        cnx.close()
    # Create table user if not exists
    try:
        cnx = mysql.connector.connect(user=uid, password=pw, host=url, database='db')
        cur = cnx.cursor()
        query = (
            "CREATE TABLE IF NOT EXISTS user("
            "   username      CHAR(10),"
            "   password    VARCHAR(20),"
            "   level       INTEGER,"
            "   PRIMARY KEY(username))"
        )
        cur.execute(query)
        cur.close()
    except mysql.connector.Error as err:
        # TO DO: error handling
        print(err)
    finally:
        cnx.close()
    # Create table inventory if not exists
    try:
        cnx = mysql.connector.connect(user=uid, password=pw, host=url, database='db')
        cur = cnx.cursor()
        query = (
            "CREATE TABLE IF NOT EXISTS inventory("
            "   id          CHAR(5),"
            "   name        VARCHAR(20),"
            "   amount      FLOAT,"
            "   unit        VARCHAR(5),"
            "   PRIMARY KEY(id))"
        )
        cur.execute(query)
        cur.close()
    except mysql.connector.Error as err:
        # TO DO: error handling
        print(err)
    finally:
        cnx.close()

# Test SQL credential
def try_SQL_credential(url, uid, pw):
    try:
        cnx = mysql.connector.connect(user=uid, password=pw, host=url)
    except mysql.connector.Error as err:
        return 0
    else:
        cnx.close()
        return 1
        
'''
# Create database if not exists
def create_database(url, uid, pw, db_name):
    try:
        cnx = mysql.connector.connect(user=uid, password=pw, host=url)
        cur = cnx.cursor()
        query = "CREATE DATABASE IF NOT EXISTS " + db_name
        cur.execute(query)
        cur.close()
    except mysql.connector.Error as err:
        # TO DO: error handling
        print(err)
    finally:
        cnx.close()
    
# Create table without checking if exists
def create_table(url, uid, pw, db_name, tb_name):
    try:
        cnx = mysql.connector.connect(user=uid, password=pw, host=url, database=db_name)
        cur = cnx.cursor()
        query = (
            "CREATE TABLE IF NOT EXISTS " + tb_name + "("
            "   id      CHAR(5),"
            "   name    VARCHAR(10),"
            "   PRIMARY KEY(id))"
        )
        cur.execute(query)
        cur.close()
    except mysql.connector.Error as err:
        # TO DO: error handling
        print(err)
    finally:
        cnx.close()
'''

# Drop database without checking
def drop_database(url, uid, pw, db_name):
    try:
        cnx = mysql.connector.connect(user=uid, password=pw, host=url, database=db_name)
        cur = cnx.cursor()
        query = ("DROP DATABASE " + db_name)
        cur.execute(query)
        cur.close()
    except mysql.connector.Error as err:
        # TO DO: error handling
        print(err)
    finally:
        cnx.close()

# Remove table without checking
def drop_table(url, uid, pw, db_name, tb_name):
    try:
        cnx = mysql.connector.connect(user=uid, password=pw, host=url, database=db_name)
        cur = cnx.cursor()
        query = ("DROP TABLE " + tb_name)
        cur.execute(query)
        cur.close()
    except mysql.connector.Error as err:
        # TO DO: error handling
        print(err)
    finally:
        cnx.close()

# Show databases
def show_database(url, uid, pw):
    try:
        cnx = mysql.connector.connect(user=uid, password=pw, host=url)
        cur = cnx.cursor()
        query = ("SHOW DATABASES")
        cur.execute(query)
        cur.close()
    except mysql.connector.Error as err:
        # TO DO: error handling
        print(err)
    finally:
        cnx.close()

# Show tables in db_name
def show_table(url, uid, pw, db_name):
    try:
        cnx = mysql.connector.connect(user=uid, password=pw, host=url, database=db_name)
        cur = cnx.cursor()
        query = ("SHOW TABLES")
        cur.execute(query)
        cur.close()
    except mysql.connector.Error as err:
        # TO DO: error handling
        print(err)
    finally:
        cnx.close()

# TO DO: INSERT, SELECT, UPDATE, DELETE
import mysql.connector

# The following codes are written with the assumption that no SQL keyword will be passed as argument
# Also, it is assumed that no malicious use, such as SQL injection, will occur

def initialize(url, uid, pw):
    # Create database db if not exists
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
    # table `user`, username is primary key
    # +----------+----------+----------+
    # | username | password |    level |
    # +----------+----------+----------+

    # Create table inventory if not exists
    try:
        cnx = mysql.connector.connect(user=uid, password=pw, host=url, database='db')
        cur = cnx.cursor()
        query = (
            "CREATE TABLE IF NOT EXISTS inventory("
            "   itemname        VARCHAR(20),"
            "   amount      FLOAT,"
            "   unit        VARCHAR(5),"
            "   PRIMARY KEY(itemname))"
        )
        cur.execute(query)
        cur.close()
    except mysql.connector.Error as err:
        # TO DO: error handling
        print(err)
    finally:
        cnx.close()
    # table `inventory`, itemname is primary key
    # +----------+----------+----------+
    # | itemname |   amount |     unit |
    # +----------+----------+----------+

# Test SQL credential
def try_SQL_credential(url, uid, pw):
    try:
        cnx = mysql.connector.connect(user=uid, password=pw, host=url)
    except mysql.connector.Error as err:
        return 0
    else:
        cnx.close()
        return 1

# Drop database without checking
def drop_database(url, uid, pw):
    try:
        cnx = mysql.connector.connect(user=uid, password=pw, host=url, database='db')
        cur = cnx.cursor()
        query = ("DROP DATABASE db")
        cur.execute(query)
        cur.close()
    except mysql.connector.Error as err:
        # TO DO: error handling
        print(err)
    finally:
        cnx.close()

# Remove table without checking
def drop_user(url, uid, pw):
    try:
        cnx = mysql.connector.connect(user=uid, password=pw, host=url, database='db')
        cur = cnx.cursor()
        query = ("DROP TABLE user")
        cur.execute(query)
        cur.close()
    except mysql.connector.Error as err:
        # TO DO: error handling
        print(err)
    finally:
        cnx.close()

# Remove table without checking
def drop_inventory(url, uid, pw):
    try:
        cnx = mysql.connector.connect(user=uid, password=pw, host=url, database='db')
        cur = cnx.cursor()
        query = ("DROP TABLE inventory")
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
        result = cur.fetchall()
        for x in result: # temporay action, change to return
            print(x)
        cur.close()
    except mysql.connector.Error as err:
        # TO DO: error handling
        print(err)
    finally:
        cnx.close()

# Show tables in db_name
def show_table(url, uid, pw):
    try:
        cnx = mysql.connector.connect(user=uid, password=pw, host=url, database='db')
        cur = cnx.cursor()
        query = ("SHOW TABLES")
        cur.execute(query)
        result = cur.fetchall()
        for x in result: # temporay action, change to return
            print(x)
        cur.close()
    except mysql.connector.Error as err:
        # TO DO: error handling
        print(err)
    finally:
        cnx.close()

def delete_user(url, uid, pw, key):
    try:
        cnx = mysql.connector.connect(user=uid, password=pw, host=url, database='db')
        cur = cnx.cursor()
        query = ("DELETE FROM user WHERE username = " + "'" + key + "'")
        cur.execute(query)
        cur.close()
        cnx.commit()
    except mysql.connector.Error as err:
        # TO DO: error handling
        print(err)
    finally:
        cnx.close()

def delete_inventory(url, uid, pw, key):
    try:
        cnx = mysql.connector.connect(user=uid, password=pw, host=url, database='db')
        cur = cnx.cursor()
        query = ("DELETE FROM inventory WHERE itemname = " + "'" + key + "'")
        cur.execute(query)
        cur.close()
        cnx.commit()
    except mysql.connector.Error as err:
        # TO DO: error handling
        print(err)
    finally:
        cnx.close()

# Select user
def select_user(url, uid, pw, key):
    try:
        cnx = mysql.connector.connect(user=uid, password=pw, host=url, database='db')
        cur = cnx.cursor()
        query = ("SELECT * FROM user WHERE username = " + "'" + key + "'")
        cur.execute(query)
        result = cur.fetchall()
        for x in result: # temporay action, change to return
            print(x)
        cur.close()
    except mysql.connector.Error as err:
        # TO DO: error handling
        print(err)
    finally:
        cnx.close()

# Select inventory
def select_inventory(url, uid, pw, key):
    try:
        cnx = mysql.connector.connect(user=uid, password=pw, host=url, database='db')
        cur = cnx.cursor()
        query = ("SELECT * FROM inventory WHERE itemname = " + "'" + key + "'")
        cur.execute(query)
        result = cur.fetchall()
        for x in result: # temporay action, change to return
            print(x)
        cur.close()
    except mysql.connector.Error as err:
        # TO DO: error handling
        print(err)
    finally:
        cnx.close()

# Insert user
def insert_user(url, uid, pw, list):
    try:
        cnx = mysql.connector.connect(user=uid, password=pw, host=url, database='db')
        cur = cnx.cursor()
        query = ("INSERT INTO user VALUES (%s, %s, %s)")
        cur.execute(query, list)
        cur.close()
        cnx.commit()
    except mysql.connector.Error as err:
        # TO DO: error handling
        print(err)
    finally:
        cnx.close()

# Insert inventory
def insert_inventory(url, uid, pw, list):
    try:
        cnx = mysql.connector.connect(user=uid, password=pw, host=url, database='db')
        cur = cnx.cursor()
        query = ("INSERT INTO inventory VALUES (%s, %s, %s)")
        cur.execute(query, list)
        cur.close()
        cnx.commit()
    except mysql.connector.Error as err:
        # TO DO: error handling
        print(err)
    finally:
        cnx.close()

# Update user
def update_user(url, uid, pw, key, column, value):
    try:
        cnx = mysql.connector.connect(user=uid, password=pw, host=url, database='db')
        cur = cnx.cursor()
        query = ("UPDATE user SET " + column + "=" + value + " WHERE username = " + "'" + key + "'")
        cur.execute(query)
        cur.close()
        cnx.commit()
    except mysql.connector.Error as err:
        # TO DO: error handling
        print(err)
    finally:
        cnx.close()

# Update inventory
def update_inventory(url, uid, pw, key, column, value):
    try:
        cnx = mysql.connector.connect(user=uid, password=pw, host=url, database='db')
        cur = cnx.cursor()
        query = ("UPDATE inventory SET " + column + "=" + value + " WHERE itemname = " + "'" + key + "'")
        cur.execute(query)
        cur.close()
        cnx.commit()
    except mysql.connector.Error as err:
        # TO DO: error handling
        print(err)
    finally:
        cnx.close()
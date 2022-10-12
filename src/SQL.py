import mysql.connector

# Create database without checking if exits
def create_database(url, uid, pw, db_name):
    try:
        cnx = mysql.connector.connect(user=uid, password=pw, host=url)
        cur = cnx.cursor()
        query = "CREATE DATABASE " + db_name
        cur.execute(query)
        cur.close()
    except mysql.connector.Error as err:
        # TO DO: error handling
        print(err)
    finally:
        cnx.close()

# Create table without checking if exits
def create_table(url, uid, pw, db_name, tb_name):
    try:
        cnx = mysql.connector.connect(user=uid, password=pw, host=url, database=db_name)
        cur = cnx.cursor()
        query = (
            "CREATE TABLE " + tb_name + "("
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
# TO DO: Modify create_table to accecpt table parameters
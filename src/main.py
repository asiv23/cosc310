from SQL import *

def testing_sql():

    # temp variables
    url = 'localhost'
    uid = 'root'
    pw = 'password'
    db_name = 'test'
    tb_name = "emp"

    create_database(url, uid, pw, db_name)
    show_database(url, uid, pw)
    create_table(url, uid, pw, db_name, tb_name)
    show_table(url, uid, pw, db_name)
    drop_table(url, uid, pw, db_name, tb_name)
    show_table(url, uid, pw, db_name)
    drop_database(url, uid, pw, db_name)
    show_database(url, uid, pw)

def main():
    # Program entry potint

    testing_sql()

if __name__ == "__main__":
    main()
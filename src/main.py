from SQL import *
from testing import test_SQL

def get_SQL_credential():
    while(1):
        # Get SQL database credential, loop breaks only when database connection is good
        print('Enter database url: ', end='')
        url = input()
        print('Enter database user name: ', end='')
        uid = input()
        print('Enter database password: ', end='')
        pw = input()
        if(try_SQL_credential(url, uid, pw)):
            print('Credential is good.')
            return [url, uid, pw]
        else:
            print('Unable to connect to database, try again.')

def main():
    # Program entry point

    cred = get_SQL_credential()
    url = cred[0] # Databse URL
    uid = cred[1] # Database user name
    pw = cred[2] # Database password

    initialize(url, uid, pw) # Creates database `db` with table `user` and `inventory` if they do not exist, else does nothing

    while(1):
        # main program loop

        shutdown = 0 # Set to 1 will break while loop and thus exit program

        #############
        # MAIN CODE #
        #############

        # testing
        test_SQL(url, uid, pw)
        shutdown = 1

        if(shutdown):
            break
    

if __name__ == "__main__":
    main()
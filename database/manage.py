from mysql.connector import MySQLConnection
import requests
import sys
import json

# Globals

config = {}


# Helper Classes

class DatabaseConnector:

    def __init__(self, db_config):
        self._db_config = db_config

    def _connect(self):
        self._conn = MySQLConnection(**self._db_config)

    def _disconnect(self):
        self._conn.close()
        self._conn = None

    def query(self, query):
        print(f"{'}'} -- [ Query : {query} ]")
        self._connect()
        cursor = self._conn.cursor()
        cursor.execute(query)
        result = []
        for row in cursor.fetchall():
            result.append(row)
        self._disconnect()
        return result

    def execute(self, path):
        self._connect()
        with open(path, 'r') as file:
            print(f"{'}'} -- [ Executing : {file} ]")
            result_iterator = self._conn.cursor().execute(file.read(), multi=True)
            for res in result_iterator:
                print(f"{'}'} -- {res}")
                print(f"{'}'} -- Rows Affected: {res.rowcount}")
            self._conn.commit()
        self._disconnect()

    def store_images(self, values):
        self._connect()
        query_template = "INSERT into recipeimages(recipe_id, image) VALUES(%s, %s)"
        cursor = self._conn.cursor()
        for row in values:
            cursor.execute(query_template, (row[0], row[1]))
        self._conn.commit()
        self._disconnect()


class Database:

    def __init__(self):
        self._load_config()
        self._connector = DatabaseConnector(self._config['db'])

    def _load_config(self):
        with open("config.json", "r") as file:
            self._config = json.load(file)
        print("} -- [ Loaded Config ]")

    def create(self, dbo):
        if dbo == 'database':
            self._create_database()
        elif dbo == 'users':
            self._create_users()
        elif dbo == 'ingredients':
            self._create_ingredients()
        elif dbo == 'recipes':
            self._create_recipes()
        elif dbo == 'recipeingredients':
            self._create_recipe_ingredients()
        elif dbo == 'images':
            self._create_images()

    def _create_database(self):
        self._connector.execute('./scripts/create_db.sql.sql')

    def _create_users(self):
        self._connector.execute('./scripts/create_users.sql')

    def _create_ingredients(self):
        self._connector.execute('./scripts/create_ingredients.sql')

    def _create_recipes(self):
        self._connector.execute('./scripts/create_recipes.sql')

    def _create_recipe_ingredients(self):
        self._connector.execute('./scripts/create_ingredients.sql')

    def _create_images(self):
        self._connector.execute('./scripts/create_images.sql')


# Admin commands

def load_images():
    print("} -- [ Started : Loading Images ]")
    connector = DatabaseConnector(config['db'])
    result = connector.query("SELECT id, thumb_url FROM recipes")
    for i in range(0, len(result), 10):
        images = []
        for j in range(0, 10):
            if (i + j) < len(result):
                images.append((result[i + j][0], requests.get(result[i + j][1]).content))
        connector.store_images(images)
        for image in images:
            print(f"{'}'} -- [ Stored Image : {image[0]} ]")

    print("} -- [ Finished : Loading Images ]")


# Main

def create(database, arg):
    if arg is None:
        print('No parameter given')
    print(f"{'}'} -- [ Creating database object : {arg} ]")
    database.create(arg)


def main():
    database = Database()
    if sys.argv[1] == 'create':
        create(database, sys.argv[2])


def print_help():
    print("} -- [ Example Command ]")
    print(" python manage.py <command> <parameter>")
    print("} -- [ Available commands ]")
    print(" create  -> Create a dbo object")
    print("} -- [ Available dbo objects ]")
    print(" database")
    print(" users")
    print(" ingredients")
    print(" recipes")
    print(" recipeingredients")
    print(" images")


if __name__ == '__main__':
    if sys.argv[1] is None:
        print("} -- [ Invalid command ]")
        print_help()
    main()

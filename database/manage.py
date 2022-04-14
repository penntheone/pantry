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
        self._connect()
        cursor = self._conn.cursor()
        cursor.execute(query)
        result = []
        for row in cursor.fetchall():
            result.append(row)
        self._disconnect()
        return result

    def store_images(self, values):
        self._connect()
        query_template = "INSERT into recipeimages(recipe_id, image) VALUES(%s, %s)"
        cursor = self._conn.cursor()
        for row in values:
            cursor.execute(query_template, (row[0], row[1]))
        self._conn.commit()
        self._disconnect()


# Admin commands

def load_config():
    with open("config.json", "r") as file:
        global config
        config = json.load(file)
    print("} -- [ Loaded Config ]")


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

def main(arg):
    print(f"{'}'} -- [ Executing : {arg} ]")
    load_config()
    if arg == 'loadimages':
        load_images()


if __name__ == '__main__':
    main(sys.argv[1])

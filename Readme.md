# Steps to take to package this with Docker

`mvn package` - Faire le package final dans /target

`docker build -t tp-ci-demo:1.0 .` - Construire l'image docker  (-t : tag,  nom et version) (. : context, où le Dockerfile est)

`docker run --rm tp-ci-demo:1.0` - Rouler le conteneur avec l'image tp-ci-demo et version 1.0 (--rm : supprimer le conteneur après qu'il arrête de rouler)


# Exemple de port 

`docker pull docker/welcome-to-docker` - Pull l'image de registre des iamges dockerhub

`docker run -d -p 8080:80 --name welcomedocker docker/welcome-to-docker`

Ne pas oublier de tester:

`docker ps`

`docker stop welcomedocker` - stop != removed

`docker rm welcomedocker`

# Notable mentions 

Homelabbing (?)
Bon pour CV, montrer sa passion

https://hub.docker.com/ - Pre-packaged apps
https://hub.docker.com/r/itzg/minecraft-server - lightweight serveur minecraft en une commande !

C'est un énorme rabbit hole, please fall into it.
Autre exemples - immich (remplacer google photos), nextcloud (drive - utilisé par l'AEDIROUM), homeassistant (smart home), jellyfin (remplacez netflix), vaultwarden (End to end encrypted password manager)

You get the point.


https://miro.medium.com/v2/resize:fit:1400/1*JkM75da_sYaK8ztL3hOGtg.gif 

# BRIEFLY - Docker compose

Pas besoin d'écrire les commandes manuellement.
Ca l'automatise
Il suffit de rouler `docker compose up -d` (-d : daemon mode)

```yaml
services:
  # Pre-built Application Image (pulls directly from Docker Hub)
  wordpress:
    image: wordpress:latest
    restart: always
    ports:
      - "8080:80"
    environment:
      WORDPRESS_DB_HOST: db
      WORDPRESS_DB_USER: wp_user
      WORDPRESS_DB_PASSWORD: wp_password
      WORDPRESS_DB_NAME: wordpress_db
    volumes:
      - wp_data:/var/www/html
    depends_on:
      - db

  # Pre-built Database Image
  db:
    image: mysql:8.4
    restart: always
    environment:
      MYSQL_DATABASE: wordpress_db
      MYSQL_USER: wp_user
      MYSQL_PASSWORD: wp_password
      MYSQL_ROOT_PASSWORD: root_password
    volumes:
      - db_data:/var/lib/mysql

volumes:
  wp_data:
  db_data:
```
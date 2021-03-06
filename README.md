# Загрузка земельных участков в БД

### Требуемое ПО

Для сборки и запуска проекта требуются:
- [Java 11 (Open JDK)](https://openjdk.java.net/projects/jdk/11/)
- [Apache Maven 3.3+](https://maven.apache.org/)

Для развертывания приложения используется механизм контейнеризации:
- [Docker](https://www.docker.com/)

### Запуск приложения

Для запуска приложения необходимо:
1. Скомпилировать и собрать проект: mvn clean package -DskipTests=true
2. Запустить создание образов и контейнеров приложения, БД в Docker: docker-compose up -d
3. В браузере набрать http://localhost:8888/ и загрузить файл кадастрового плана территории
4. Проверить в БД наличие данных по земельным участкам

При локальном запуске из IDEA в **Edit Configurations..** установить -Dspring.profiles.active=local в **VM Options:**

## Лицензия
[![GPLv3 license](https://img.shields.io/badge/License-GPLv3-blue.svg)](http://perso.crans.org/besson/LICENSE.html)

  Code released under the GNU General Public License v3.0
  
## Контакты
  Created by [@akardapolov](mailto:akardapolov@gmail.com)
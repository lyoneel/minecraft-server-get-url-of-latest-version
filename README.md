# minecraft-server-update-url-generator

I did this little program to get direct url to download latest version of minecraft from my personal server.
The problem is minecraft didn't provide a direct url, you have to manually get the url unless you parse two JSON files to get the right value.

This program only show data for the latest stable version of minecraft server.

## Usage
`java -jar <this-jar>.jar [version] [sha|sha1] [url] [size]` 

If you run it without any parameter the program will return all data available.

#### Output examples at 2021-02-28
If you run it without any parameter
```
1.16.5
https://launcher.mojang.com/v1/objects/1b557e7b033b583cd9f66746b7a9ab1ec1673ced/server.jar
1b557e7b033b583cd9f66746b7a9ab1ec1673ced
37962360
```

You can specify the specific information you want to know, in this case just the version:

`java -jar <this-jar>.jar url`

The output will be:

`https://launcher.mojang.com/v1/objects/1b557e7b033b583cd9f66746b7a9ab1ec1673ced/server.jar`

You can use multiple parameters:

`java -jar <this-jar>.jar url sha`

Output:

```
https://launcher.mojang.com/v1/objects/1b557e7b033b583cd9f66746b7a9ab1ec1673ced/server.jar
1b557e7b033b583cd9f66746b7a9ab1ec1673ced
```

And to be future-proof you can also specify the main JSON url

`java -DMC_MAIN_JSON_URL=newUrlToJson -jar <this-jar>.jar`

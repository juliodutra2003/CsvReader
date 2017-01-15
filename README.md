# CsvReader

CsvReader is a JAVA command line software designed to read, parse, and query files.
In the current version, CsvReader supports only .csv files, but can be extended to support other formats.

## How to use:

To run your generated .jar file you need to pass the name of your data source file as parameter, like the image below:
![GitHub Logo](https://i.gyazo.com/1c938c56eb843ad6913cc21f2b88b3e0.png)

Use the following commands as showing below:
* help (show help menu)
* quit (quit program)
* show (show all valid properties)
* file (show all lines)
* file count (show a set of lines, if a max count equals to count) 
* count * (show the count of registers) 
* count disctint _propertyname_ (show the total count of [distinct] values of a [property] ) 
* filter property _value_ (show all lines where a [property] has the [value])

## Important:

1) Files need to be in UTF-8 character enconde

2) If you pass any parameter with more then one word, you need to use quotes ('):
* filter [property] ['_value with more then a world_'] 
  *  e.g. >filter carfuel 'eletric cars'

3) If you have some problems to show some characters on windows cmd: 
* change font to Lucida Console
* run _'chcp 1252'_ on console before start Csvreader

## TODO:
* List of files to read and operate, instead of directly read from command line as a parameter;
* Support to XML files
* Support to JSON files

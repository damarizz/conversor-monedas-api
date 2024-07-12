# Currency Converter Challenge
It is intended to challenge us to make requests to an exchange rate API, manipulate JSON data, and finally filter and display the currencies of interest.

### Requirements
- Java JDK: version 11 onwards
- Gson Library 2.10.1 onwards
- Exchange rate API for conversion
  - In `src/main/resources`, create a `config.properties` file where you will place your API key, based on the template found in the same folder.
    ``` config.properties.template
    # API configuration
    api.key=YOUR_API_KEY_HERE

    # Other settings can be added below
    # example.config=example_value
    ```
  ps: `config.properties` is already marked on `.gitignore` :) 

### Structure
- MVC pattern
```
│── src/
│   └── main/
│   │       └── java/
│   │           ├── Controller/
│   │           │   └── API.java
│   │           ├── Model/
│   │           │   └── Currency.java
│   │           └── View/
│   │               ├── Main.java
│   │               └── Menu.java
│   └── resources/
│   │   ├── config.properties
│   │   └── config.properties.template
```
### Execution
- Run `Main.java` in `src/main/java/View` and choose your source and target currencies!
- To add more currencies, simply add more Currency objects to the ArrayList. 
  - WARNING: The currencies added to this ArrayList must be present in  the ExchangeRate-API supported currencies list:
   https://www.exchangerate-api.com/docs/supported-currencies
  - The `code` (Currency Code) must match the one mentioned in the documentation. `name` can be chosen at your discretion.


\
_This training is part of the ONE Program, an alliance between Alura Latam and Oracle_

<img src="https://aprende.goodneighbors.cl/wp-content/uploads/2022/02/ONE_logo_rgb-768x408.png" alt="Logo de Oracle Next Education" width="100">
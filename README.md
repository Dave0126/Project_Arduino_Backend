# Project_Arduino_Backend

## 开发环境
- `JDK 11`
- `Maven 3.8.5`
- `IDEA`

## 本地依赖
- `libs/openfoodfacts-changed-1.0.001-SNAPSHOT.jar`: 更改了官方提供的工具类，解决了之前解析 `JSON` 文件时，映射的实体类有部分属性为 `null` 的问题。
  
  更改之前：
  
  ```json
  {
      "product_name_en": "",
  	"product_name_fr": "Red Bull Energy Drink",
  	"product_name_nl": "RedBull",
  	"product_quantity": "250",
  	"purchase_places": "Saint-Maur des Fossés,France"
  }
  ```
  
  更改之后：
  
  ```json
  {
      "product_name": "Red Bull Energy Drink",
      "product_name_en": "",
      "product_name_fr": "Red Bull Energy Drink",
      "product_name_nl": "RedBull",
      "product_quantity": "250",
      "purchase_places": "Saint-Maur des Fossés,France",
  }
  ```




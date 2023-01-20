# Project_Arduino_Backend

## 开发环境
- `JDK 11`
- `Maven 3.8.5`
- `IDEA`
- 前端开发项目 (Frontend Part)：[Arduino_Project_Frontend](https://github.com/Dave0126/Arduino_Project_Frontend)

## 本地依赖
- `libs/openfoodfacts-changed-1.0.001-SNAPSHOT.jar`: 更改了官方提供的工具类，并重新打包。解决了之前解析 `JSON` 文件时，映射的实体类有部分属性为 `null` 的问题。
  
  > `libs/openfoodfacts-changed-1.0.001-SNAPSHOT.jar`: Changed the official tools and repackaged them. Solved the problem that when parsing `JSON` files, some properties of the mapped entity class were `null`.
  
  Before Changed：
  
  ```json
  {
      "product_name_en": "",
  	"product_name_fr": "Red Bull Energy Drink",
  	"product_name_nl": "RedBull",
  	"product_quantity": "250",
  	"purchase_places": "Saint-Maur des Fossés,France"
  }
  ```
  
  After Changed：
  
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



## 前后端信息传递格式

### `Result`

为了前端处理方便，封装 `Result<T>` 类进行数据响应。其格式如下：

> For the convenience of front-end processing, the `Result<T>` class is encapsulated for data response. Its format is as follows:

`fr.gdai.ap.utils.Result`

```java
package fr.gdai.ap.utils;

public class Result<T> {
    private Integer resultCode;
    private T data;
    private String msg;
}
```

其中统一定义了 `resultCode` 作为后端返回的操作码，规定如下

> Among them, `resultCode` is uniformly defined as the operation code returned by the backend, as follows

```java
package fr.gdai.ap.utils;

public class ResultCode {
    public static final Integer INSERT_SUCC = 20011;
    public static final Integer DELETE_SUCC = 20021;
    public static final Integer UPDATE_SUCC = 20031;
    public static final Integer SELECT_SUCC = 20041;

    public static final Integer INSERT_ERR = 20010;
    public static final Integer DELETE_ERR = 20020;
    public static final Integer UPDATE_ERR = 20030;
    public static final Integer SELECT_ERR = 20040;

    public static final Integer BARCODE_FOUND = 30011;
    public static final Integer BARCODE_NOTFOUND = 30010;

    public static final Integer USER_FOUND = 10011;
    public static final Integer USER_NOTFOUND = 10010;
    public static final Integer USER_EXIST = 10020;
    public static final Integer USER_INSERT_SUCC = 10031;
}
```

因此，经由 `Result` 封装过后的结果，以 `JSON` 格式发送到前端浏览器的格式如下：

> Therefore, the format of sending the result encapsulated by `Result` to the front-end browser in `JSON` format is as follows:

```
{
    "resultCode": ,
    "data": ,
    "msg": 
}
```

### `fr.gdai.ap.controller.ProductController`

#### `getProduct(String barcode): Result`

- 前端：使用 `GET` 请求。

  > Frontend: use `GET` requests.

  | 映射路径 (Mapping Path) | 参数名 (Param.) | 值类型 (Type) |
  | ----------------------- | --------------- | ------------- |
  | `/product/search`       | `barcode`       | `String`      |

  - 示例 (Example)：`/arduino_project/product/search?barcode=3228886043714`

- 后端：

  服务器响应内容格式如下

  > Backend: The format of the server response content is as follows
  
  ```json
  {
      "resultCode": 20041,
      "data": {
          "name": "productName",
          "barcode": "barCode_int_String",
          "ingredients": [{...}, {...}, {...}, {...}, {...}],	// Top 5 ingredients
          "quantity": "quantity_int_String",
          "nutriments": {
              "calcium": 0.0,			// float
              "carbohydrates": 0.0,	// float
              "energy": 0				// int
          }
      },
      "msg": "Message"
  }
  ```

### `fr.gdai.ap.controller.UserController`

#### `register(User user): Result`

- 前端：使用 `POST` 请求。

  > Frontend: use `POST` requests.

  | 映射路径  (Mapping Path) | 参数名 (Param.) | 值类型 (Type) |
  | ------------------------ | --------------- | ------------- |
  | `user/register`          | `name`          | `String`      |
  |                          | `age`           | `int`         |

  - 示例 (Example)：`/arduino_project/user/register`

    请求体：`JSON` 格式

    > Request body: `JSON` format
    
    ```json
    {
        "name": "gdai",
        "age": 24
    }
    ```

- 后端 Backend：

  服务器端（不带数据库）中维护一个 `HashMap<String,User>`，每个 `resigter()` 的新用户 `User` 都会在 `HashMap` 中检索是否存在以 `User.name` 为键的值 `User`。若存在，则返回如下内容：

  > A `HashMap<String,User>` is maintained on the server side (without database), and each new user `User` of `resigter()` will search whether there is a `User.name` as the key in `HashMap` The value `User`. If it exists, return the following:
  
  ```json
  {
      "resultCode": 10020,
      "data": null,
      "msg": "User register failed: This user is existed"
  }
  ```

  若不存在，则将其注册在 `HashMap` 中，并返回如下内容：
  
  > If it does not exist, it is registered in the `HashMap` and returns the following:
  
  ```json
  {
      "resultCode": 10031,
      "data": null,
      "msg": "User register successfully"
  }
  ```



#### `addProductByUser(String username, String barcode): Result`

- 前端：使用 `GET` 请求。

  > Frontend: use `GET` requests.

  | 映射路径 (Mapping Path) | 参数名 (Param.) | 值类型 (Type) |
  | ----------------------- | --------------- | ------------- |
  | `user/addProduct`       | `username`      | `String`      |
  |                         | `barcode`       | `String`      |

  - 示例 (Example)：`/arduino_project/user/addProduct?username=gdai&barcode=3228886043712`

  

- 后端 Backend：

  首先，后端在接收到该 `GET` 请求后，会在内部维护的 `HashMap<String,User>` 中查询以 `username` 为键的 `User` 对象是否存在，如不存在，则返回：

  > First, after receiving the `GET` request, the backend will query whether the `User` object with `username` as the key exists in the internally maintained `HashMap<String,User>`, if not, it will return:
  
  ```json
  {
      "resultCode": 10010,
      "data": null,
      "msg": "The username you entered does not exist!"
  }
  ```

  其次，后端服务器会向 *OpenFoodFacts* 组织提供的 `API` 查询关于条形码 `barcode` 关联的商品信息。若没有查找到，则返回：
  
  > Second, the backend server will query the `API` provided by the *OpenFoodFacts* organization about the product information associated with the barcode `barcode`. If not found, returns:
  
  ```json
  {
      "resultCode": 30010,
      "data": null,
      "msg": "The barcode you entered does not exist!"
  }
  ```
  
  再次，如果后端服务器在向 `User` 类中的 `productList` 中添加 `add()` 查找到的商品 `product` 时出现错误，则返回：
  
  > Again, if the backend server encounters an error when adding the product `product` found by `add()` to the `productList` in the `User` class, it will return:
  
  ```json
  {
      "resultCode": 20010,
      "data": null,
      "msg": "Failed to add product for user!"
  }
  ```
  
  最后，如果上面的步骤都没有出错，则可以成功地为 `User` 添加 `Product`。返回如下所示：
  
  > Finally, if none of the above steps went wrong, you can successfully add `Product` for `User`. returns something like this:
  
  ```json
  {
      "resultCode": 20011,
      "data": {
          "name": "UserName",
          "age": 0,
          "productList": [
              {
                  "name": "ProductName",
                  "barcode": "BarCode",
                  "ingredients": [{...}, {...}, {...}, {...}, {...}],
                  "quantity": "Quantity_int",
                  "nutriments": {
                      "calcium": 0.0,			// float
                      "carbohydrates": 0.0,	// float
                      "energy": 0				// int
                  }
              },
              {...}
          ],
          "dailyEnergy": 0,			// 每日摄入能量总量, 单位KCal, int
          "dailyCalcium": 0.0,		// 每日摄入钙总量, 单位g, float
          "dailyCarbohydrates": 23.0	// 每日摄入能量总量, 单位g, float
      },
      "msg": "Successfully add product for user"
  }
  ```
  
  


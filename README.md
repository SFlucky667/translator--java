##### demo
> API：
> 1.注册开发者账号：在选定的电商平台上进行开发者账号注册。一般需要填写一些基本信息，如企业或个人的相关资料，并可能需要通过平台的审核。      
   2.获取 API 密钥：成功注册开发者账号后，平台会为您提供 API 密钥，通常包括客户端 ID 和客户端密钥。这些密钥用于验证 API 调用的身份，确保只有经过授权的开发者才能访问和使用相关 API 服务。                                    
   3.阅读 API 文档：仔细研读电商平台提供的 API 文档，这是非常关键的一步。文档中会详细说明 API 的功能、参数、请求方式（如 GET、POST 等）、请求限制（例如每秒请求次数限制、访问频率限制等）以及返回数据的格式等重要信息。只有充分理解这些内容，才能正确地调用 API 接口。    
   4.开发与测试：依据 API 文档，使用您熟悉的编程语言和开发工具编写代码来调用 API 接口。在开发过程中，要注意按照文档要求正确构建请求参数和处理返回结果。为了确保 API 的正常运行和功能符合预期，需要在沙盒环境（如果电商平台提供）或模拟测试环境中进行充分的测试。    

获取百度翻译的API
>要获取百度翻译的API，可以按照以下步骤进行：

### 1. 注册百度智能云账号
如果还没有百度账号，请先注册一个百度账号并登录百度智能云：[百度智能云](https://cloud.baidu.com/)。

##### 2. 申请百度翻译API服务
登录后，在百度智能云首页上搜索“翻译”或进入**[百度翻译开放平台](https://fanyi-api.baidu.com/)**，找到并选择“翻译API”服务。

#### 3. 创建应用
在百度翻译开放平台中，点击“控制台”或“立即使用”，创建一个新应用。应用的创建步骤包括：
   - 填写应用名称
   - 选择所属类别
   - 提交后即可创建成功

#### 4. 获取 API 密钥（APP ID 和密钥）
创建应用成功后，您会在应用的详情页面中看到**App ID**和**密钥**（Secret Key）。这两个值在后续的API请求中用来认证身份，因此需要妥善保存。

##### 5. 查看API文档
在百度翻译开放平台中查看API文档，了解如何使用该API。文档中会包括可用的端点（如翻译接口、语言检测接口等）、参数说明、调用方法、错误码等信息。

##### 6. 测试API
在获取到App ID和密钥后，可以测试一下API。您可以使用cURL或Postman等工具发送测试请求。百度翻译API的典型调用格式如下：

```plaintext
https://fanyi-api.baidu.com/api/trans/vip/translate?q=待翻译的文本&from=源语言&to=目标语言&appid=您的App ID&salt=随机数&sign=签名
```

其中：
   - **q**：待翻译的文本
   - **from**：源语言代码，如“zh”代表中文
   - **to**：目标语言代码，如“en”代表英文
   - **appid**：您的应用ID
   - **salt**：随机数
   - **sign**：签名（用密钥生成，见文档）

**签名的生成方式**：签名是通过将`appid`、`q`、`salt`和`密钥`按顺序拼接后进行MD5加密得到的字符串，用于验证请求合法性。

####  7. 集成API到应用
根据文档中的说明，将API集成到您的代码中，并确保在代码中正确处理App ID、密钥和签名的生成。

完成上述步骤后，您就可以在您的应用中使用百度翻译API了。

#### 4. 处理 API 响应

百度翻译 API 的响应通常是 JSON 格式。上面代码的 `content` 包含整个 JSON 响应，您可以使用 JSON 解析库（如 `org.json` 或 `Gson`）将响应解析为结构化数据并提取翻译结果。

#### 5. 测试和集成到应用

在集成到实际应用中之前，您可以运行这段代码进行测试。确保：

- `APP_ID` 和 `SECRET_KEY` 正确
- 签名生成无误
- 请求成功，并能正确解析 JSON 响应

##### 实践
* 导入依赖：
            - `import java.security.MessageDigest;`： 导入 Java 的 `MessageDigest` 类，这是一个提供消息摘要（哈希）功能的类。它可以生成指定算法（如 MD5、SHA）的哈希值。常用于加密、数据完整性验证、数字签名等操作。
            - `import com.fasterxml.jackson.databind.ObjectMapper;`: 导入 ObjectMapper 类，它是 Jackson 库的一部分。Jackson 是一个用于 JSON 处理的库，ObjectMapper 用于将 Java 对象和 JSON 数据之间进行转换。
                summary： 将 Java 对象转换为 JSON 字符串，或者将 JSON 字符串解析为 Java 对象。
            - `import java.net.HttpURLConnection`：Java 提供的类，用于与 HTTP 服务器通信
            -`import java.net.URL`:表示统一资源定位符，用于表示请求目标。
            -`import java.io.BufferedReader`` import java.io.InputStreamReader`:用于读取 HTTP 响应流。
            -`import com.fasterxml.jackson.databind.JsonNode`:**`JsonNode`** 是一个表示 JSON 树中节点的对象，允许逐步访问 JSON 数据结构。
            -`import com.fasterxml.jackson.databind.ObjectMapper`:**`ObjectMapper`** 是 Jackson 库中的核心类，用于将 JSON 字符串解析为 Java 对象或将 Java 对象序列化为 JSON。
            

#### 1.BaiduTranslation.java
>第一块：`构建请求URL，发生http get请求，解析响应` `
> * **`Confinguration` 类**：
    - 应该是一个配置类，用于管理程序运行所需的配置信息（如 API 的 URL、应用 ID、密钥等）。
    - 一般会通过读取配置文件（如 `.properties` 或 `.yaml`）、环境变量，或者直接硬编码来加载配置信息。
>* **`getInstance()` 方法**：
    - 这是一个常见的设计模式，称为**单例模式（Singleton Pattern）**。
    - 通过单例模式，确保整个程序中只有一个 `Confinguration` 实例，方便管理和使用配置数据。 
 * `String.valueOf()`功能--将其他数据类型（如`long`、`int`、`double`等）转换为`String`类型。
>  第二块：功能是生成一个 MD5 哈希值，用于签名验证，具体用途是在调用 API 时，生成一个合法的签名以确保数据的完整性和身份认证。
    *  拼接待加密的字符串，将 `APP_ID`、`query`、`salt` 和 `SECURITY_KEY` 按顺序组合成一个完整的字符串。
    * 创建一个 MD5 摘要对象，MessageDigest 是 Java 提供的哈希计算工具。参数---"MD5" 指定哈希算法为 MD5。
    * 使用 MD5 算法计算输入字符串 sign 的哈希值，返回一个**字节数组**。关键点--sign.getBytes("UTF-8")--将字符串 sign 按 UTF-8 编码转换为字节数组。md.digest(...)--计算字节数组的 MD5 哈希值。
    * 创建一个 `StringBuilder` 对象，用于拼接十六进制格式的字符串。
    * 将字节数组 messageDigest 转换为十六进制字符串。关键点--0xff & b：将字节 b 转换为无符号整数，确保结果在 0~255 之间。Integer.toHexString(...)：将整数转换为对应的十六进制字符串。if (hex.length() == 1)：如果十六进制字符串只有一位，在前面补一个 0，保证每个字节都用两位表示（如 0a 而不是 a）。hexString.append(...)：将转换后的十六进制字符串追加到 StringBuilder 中。
> 第三块：功能是解析一个 JSON 格式的字符串 `response`，将其转换为一个 Java 对象 `BaiduTranslationResult`。
>    * 将 JSON 响应字符串解析为 `BaiduTranslationResult` 类型的对象。
>    * 创建一个 ObjectMapper 对象，它是 Jackson 数据处理库中的核心类。作用--用于将 JSON 字符串转换为 Java 对象（反序列化），或者将 Java 对象转换为 JSON 字符串（序列化）。
>    * 声明一个 `BaiduTranslationResult` 类型的变量，用于存储解析后的结果。
>    * 使用 ObjectMapper 的 readValue 方法，将 JSON 字符串解析为 BaiduTranslationResult 对象。参数说明--response：待解析的 JSON 字符串。BaiduTranslationResult.class--指定目标类型，即解析的结果应该是一个 BaiduTranslationResult 对象。
#### 2..BaiduTranslationResult.java
> * 定义了一个名为 `BaiduTranslationResult` 的类，用于存储百度翻译API的返回结果。
> * 类中包含三个私有成员变量：`from` 表示源语言，`to` 表示目标语言，`trans_result` 是一个 `List` 类型的集合，用于存储翻译结果。
> * 此外，还提供了一个默认的无参构造函数。
> * 
#### 3.Confinguration.java(配置)
**用于管理配置设置。**
>* 单例模式：其目的是保证系统中一个类仅有一个实例，并且该类给外部提供一个访问它实例的方法
>* `Properties` 是一个用于管理配置信息的类，继承自 `Hashtable`，专门设计用于存储 **键值对** 格式的字符串数据（通常是配置文件的数据，如 `.properties` 文件）。它的常见功能包括 **读取配置文件**、**保存配置** 和 **访问键值对**。
#### 4.UrlBuilder.java
**帮助构建 URL 地址的工具类。**
>第一块：类定义和手段，第二块：构造函数，第三块：添加查询参数，第四块：构建完整URL
>* 哈希表（Hash Table）是一种通过 **哈希函数** 将键**映射**到数组位置的结构，核心思想是将数据存储在一个 **固定大小的数组** 中，按需解决冲突。
>  - `Map` 是一个接口，定义了键值对（key-value）的存储规则和操作行为。常见实现包括 `HashMap`、`TreeMap`、`LinkedHashMap` ，每个键（key）对应一个值（value）
>    - `HashMap` 是一种基于哈希表的数据结构，提供了键值对（key-value）的存储和快速访问。它常用于在需要快速插入、查找、删除的场景中。![[Pasted image 20241122160617.png]]
>    - `LinkedHashMap` 是 `HashMap` 的扩展版本，额外维护了插入顺序或访问顺序，说白了，保证参数的顺序与添加时一致。
>    第四块解析：build()；
>    - **`StringBuilder`**: 用于高效构建字符串，避免频繁的字符串拼接操作。
    -**`baseUrl`**: URL 的基础部分被添加到 `url` 中。
    -`queryParams.isEmpty()`,查询参数是否为空
    -遍历查询参数:增强型 `for` 循环遍历 `queryParams` 的每个键值对(用于遍历集合或数组)
     格式：for (类型 变量名 : 集合/数组) {
           // 循环体
            }
        - 此循环变量名为`entry`    
           每次循环时：`entry` 被赋值为当前的键值对，可以通过 `entry.getKey()` 和`entry.getValue()` 来访问键和值。
        - `URLEncoder.encode()`:对键和值进行URL编码，将特殊字符转换为安全的编码形式
        - `return url.toString()`:返回完整的URL字符串

#### 5.HttpUtils.java
**此工具类提供了一个静态方法 `Get`，用于发送 HTTP GET 请求，并返回服务器的响应内容作为字符串**
>- 实现了一个HTTP GET请求，用于从指定的URL获取数据
>- 并获取响应内容
>   第一块：类定义    第二块：回应对象初始化（创建 `StringBuilder` 对象 `response` 用于存储响应内容）   第三块：URL 和连接初始化   第四块：检查响应状态码    第五块：读取响应内容         第六块：处理非 200 响应（else部分） 第七块：返回响应内容
>   - **第二块解析**：创建 `StringBuilder` 对象 `response` 用于存储响应内容
>   - **第三块解析**：通过 `URL` 对象打开一个 `HttpURLConnection` 连接，调用 `openConnection()` 方法获取 HTTP 连接对象
>   - **第四块解析**：获取 HTTP 响应代码，检查响应代码是否为 `200 OK`（即请求成功）。
>   - **第五块解析**：使用 `BufferedReader` 逐行读取服务器返回的内容，并追加到 `StringBuilder` 中（调用response）。
>   - **第六块解析**：如果响应代码不是 `200`，抛出运行时异常，并将错误代码包含在消息中。
>   - **第七块解析**：将 `StringBuilder` 转换为字符串返回。

#### 6.ParseTranslationResult.java（解析翻译结果）
>**JSON**（JavaScript Object Notation，JavaScript 对象表示法）是一种轻量级的数据交换格式，易于人阅读和书写，同时也便于机器解析和生成。它是由键值对组成的结构化数据格式，广泛应用于数据传输和存储场景，特别是在 Web 应用和 API 开发中。

**此工具类用于解析翻译接口的返回 JSON 数据，并提取翻译后的文本内容。它利用了 Jackson 库来处理 JSON 数据。**
>重要部分解析（**试解析JSON**）：
>-**创建 JSON 解析器**:实例化 Jackson 的 `ObjectMapper`，用于解析 JSON 数据。
>-**解析 JSON 数据**:使用 `ObjectMapper` 的 `readTree` 方法将 JSON 字符串解析为一个 `JsonNode` 对象，代表整个 JSON 数据的树结构。
>-**提取翻译结果**:root.path("trans_result")：获取 JSON 中 trans_result 节点的值。
            .get(0)：获取 trans_result 数组的第一个元素。
            .path("dst")：获取该元素中的 dst 属性值，表示翻译后的目标文本。
            .asText()：将 dst 的值转换为字符串并返回。

#### 7.Main.java
#### 8.config.properties
记得创建.....................                        
![[Pasted image 20241226222346.png]]
>   - 它是一个 **属性文件**，常用于存储配置信息或应用程序的设置。它是 Java 开发中非常常见的一种  配置文件格式。
>   - 创建方式，Floder---Floder---File(直接输入名称)
>   - 管理控制台查找最后俩行的信息 

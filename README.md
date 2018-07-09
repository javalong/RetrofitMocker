# RetrofitMocker
Retrofit Mock Data Efficiently

1. 引入依赖
Add it in your root build.gradle at the end of repositories:
```gradle
allprojects {
		repositories {
			...
			maven { url 'https://www.jitpack.io' }
		}
	}
```
Step 2. Add the dependency
```gradle
	dependencies {
	        implementation 'com.github.javalong:RetrofitMocker:1.0.0'
	}
```

2. 定义接口时使用@MOCK注释
```java
    //不使用MOCK注解
    @GET(" ")
    fun test1():Call<String>

    //使用MOCK注解，mock本地数据，指定assets文件夹中的test_1.json文件为mock数据
    @MOCK("test_1.json")
    @GET(" ")
    fun test2():Call<String>

    //使用MOCK注解，mock远程数据，重新指定url地址
    @MOCK("https://api.github.com/users")
    @GET(" ")
    fun test3():Call<String>

    //支持RxJava+Retrofit的方式
    @MOCK("test_1.json")
    @GET(" ")
    fun test4():Observable<String>
```
这里不管你使用`Call`,还是使用`Observable`,返回的是 `String`还是对象，都可以，这个框架不会对你的原来的使用造成任何影响。


3. 使用createMocker来获取代理对象
```java
 var retrofit = Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()

serviceApi = retrofit.createMocker(ServiceApi::class.java)
//建议这样写，发布就不会忘了开关 serviceApi = retrofit.createMocker(ServiceApi::class.java,BuildConfig.DEBUG)
//如果没有使用 kotlin 可以这么写 serviceApi = MockerHelper.createMocker(retrofit,ServiceApi::class.java,BuildConfig.DEBUG)
  
```
提供了2种方式获取代理对象
1. 使用了kotlin，我在框架中添加了`Retrofit`对象的扩展方法，原本使用`retrofit.create`的地方替换为`retrofit.createMocker`即可。

2. 不使用kotlin，直接调用`MockerHelper.createMocker`获取

      
    注意：这里最后使用BuildConfig.DEBUG当作参数传入，作为是否启用mock数据的开关，防止发布时遗忘。
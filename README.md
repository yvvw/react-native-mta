# react-native-mta



## 安装

```bash
yarn add @yyyyu/react-native-mta
```

or

```bash
npm install --save @yyyyu/react-native-mta
```



## 配置

### ios

#### 1. 自动配置(推荐)

```bash
react-native link @yyyyu/react-native-mta
```

如果项目使用 Pods 管理依赖需要在 Podfile 中添加

```ruby
pod 'React', :path => '../node_modules/react-native', :subspecs => ['Dependency']
```

#### 2. 手动配置

1. 使用 Xcode 打开项目，在项目依赖目录(Libraries)下添加 node_modules 中的 react-native-mta 项目
2. 在 Linked Frameworks and Libraries 添加 libRNMta.a

#### 额外配置(使用 Pods 管理依赖不需要进行此操作)

在 Linked Frameworks and Libraries 添加 libsqlite3.tbd

### android(以下 2 种方式选择一种)

#### 1. 自动配置(如果 IOS 已经运行过，不需要重复运行)

```bash
react-native link @yyyyu/react-native-mta
```

#### 2. 手动配置

1. 在 android/settings.gradle 文件中添加

   ```Groovy
   include ':react-native-mta'
   project(':react-native-mta').projectDir = new File(rootProject.projectDir, '../node_modules/@yyyyu/react-native-mta/android')
   ```

2. 在 android/app/build.gradle 文件中依赖部分添加

  ```Groovy
  dependencies {
      // other dependencies
      compile project(':react-native-mta')
  }
  ```

3. 在 MainApplication.java 文件中添加

  ```Java
  import com.rnlib.mta.RNMtaPackage;

  @Override
  protected List<ReactPackage> getPackages() {
      return Arrays.<ReactPackage>asList(
  		// other packages
          new RNMtaPackage()
      );
  }
  ```

#### 额外配置

在 android/app/build.gradle 文件默认配置部分添加如下参数，此部分可以通过代码动态覆盖，gradle 同步时不添加则会出错无法编译

```Groovy
android {
    defaultConfig {
    	// add this
        manifestPlaceholders = [
            MTA_APPKEY: "",
            MTA_CHANNEL: ""
        ]
    }
}
```



## JS API

```javascript
import mta from '@yyyyu/react-native-mta'

mta.startWithAppkey({ appKey: 'appKey' })
  .then(res => { console.log(res) })
  .catch(err => { console.error(err) })
```

### 参数说明

1. 参数注释带有 optional 字样为可选参数，括号内为默认值，e.g. optional('default')

2. iosOnly androidOnly 为只有在相应平台才会生效

### 返回值说明

1. 所有函数均以 es7 async 形式调用，react native 环境下会通过 babel 自动转换

2. 如果未调用 startWithAppkey 初始化，调用其它函数会抛出异常，初始化后除 CustomEvent 系列函数有精确错误描述外，其余函数均以返回 boolean 表示调用成功或失败

3. 返回类型

   - Boolean

   - Object

     ```javascript
     { errCode: 0, errMsg: '' }
     { errCode: -1, errMsg: 'MTA初始化未成功' }
     { errCode: -2, errMsg: 'MTA传入参数错误' }
     { errCode: -3, errMsg: 'MTA传入参数过长' }
     { errCode: -9, errMsg: '未知错误' }
     ```

     ​

#### startWithAppkey 初始化 MTA 模块

```javascript
startWithAppkey({
  appKey: 'you app key', // mta 管理后台中拿到的 appKey(*ios android 不同*)
  channel: 'channel',    // androidOnly optional('')
  isDebug: false,        // androidOnly optional(false)
})
```

#### trackPageBegin 跟踪页面打开

```javascript
trackPageBegin({
  page: 'page_id',      // 页面标识
  appKey: 'you app key' // iosOnly optional(初始化 appKey)
})
```

#### trackPageEnd 跟踪页面关闭

```javascript
trackPageBegin({
  page: 'page_id',       // 跟踪页面标识
  appKey: 'you app key', // iosOnly optional(初始化 appKey)
  isRealTime: false      // iosOnly optional(false) 是否实时上报数据
})
```

#### trackCustomEvent 自定义事件

```javascript
trackCustomEvent({
  event: 'event_id',     // 事件标识
  params: {},            // optional({}) 事件参数
  appKey: 'you app key', // iosOnly optional(初始化 appKey)
  isRealTime: false      // iosOnly optional(false) 是否实时上报数据
})
```

#### trackCustomEventBegin 自定义事件开始

```javascript
trackCustomEvent({
  event: 'event_id',     // 事件标识
  params: {},            // optional({}) 事件参数
  appKey: 'you app key', // iosOnly optional(初始化 appKey)
  isRealTime: false      // iosOnly optional(false) 是否实时上报数据
})
```

#### trackCustomEventEnd 自定义事件结束

```javascript
trackCustomEvent({
  event: 'event_id',     // 事件标识
  params: {},            // optional({}) 事件参数
  appKey: 'you app key', // iosOnly optional(初始化 appKey)
  isRealTime: false      // iosOnly optional(false) 是否实时上报数据
})
```

#### trackCustomEventDuration 持续一段时间的自定义事件

```javascript
trackCustomEvent({
  event: 'event_id',     // 事件标识
  duration: 1000,        // 事件持续时间
  params: {},            // optional({}) 事件参数
  appKey: 'you app key', // iosOnly optional(初始化 appKey)
  isRealTime: false      // iosOnly optional(false) 是否实时上报数据
})
```

#### trackActiveBegin 跟踪应用进入前台

```javascript
trackActiveBegin() // iosOnly
```

#### trackActiveEnd 跟踪应用进入后台

```javascript
trackActiveEnd() // iosOnly
```

#### setUserProperty 设置自定义参数

```javascript
setUserProperty({ customKey: 'customValue' })
```

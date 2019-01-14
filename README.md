# ProtoBuffDemo
A simple demo on Protocol Buffer
关于Protocol Buffer就不多介绍了，网上有很多详细的介绍，这里主要描述一些protobuf的用法

在Android studio上，配置关于protobuf的信息
1、在project的build.gradle中配置插件
classpath 'com.google.protobuf:protobuf-gradle-plugin:0.8.7'
，后面的版本号，可参考：https://mvnrepository.com/artifact/com.google.protobuf/protobuf-gradle-plugin
2、在app的build.gradle中配置
apply plugin: 'com.google.protobuf'

protobuf {
    protoc {
        // 编译器版本
        artifact = 'com.google.protobuf:protoc:3.6.1'
    }
    plugins {
        javalite {
            // 指定当前工程使用的protobuf版本为javalite版，以生成javalite版的java类
            artifact = 'com.google.protobuf:protoc-gen-javalite:3.0.0'
        }
    }
    
    generateProtoTasks {
        all().each { task ->
            task.builtins {
                remove java
            }
            task.plugins {
                javalite {}
            }
        }
    }
}

dependencies {
    implementation fileTree(include: ['*.jar'], dir: 'libs')
    implementation 'com.android.support:appcompat-v7:28.0.0'
    implementation 'com.android.support.constraint:constraint-layout:1.1.3'
    testImplementation 'junit:junit:4.12'
    androidTestImplementation 'com.android.support.test:runner:1.0.2'
    androidTestImplementation 'com.android.support.test.espresso:espresso-core:3.0.2'
    implementation 'com.google.protobuf:protobuf-lite:3.0.1'
}

3、接下来就是新建.proto文件了，可以安装一个Android studio的插件Protobuf Support,便于编辑。
  具体的写法在demo中
  
 在网上看了很多大神的资料，在这里附上连接：
 1:https://blog.csdn.net/briblue/article/details/53187780
 2:https://blog.csdn.net/qq_35599978/article/details/80386356
 3；https://www.jianshu.com/p/df200894f5da

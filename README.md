# JMPCFramework
### JMFramework for Netbeans Java Application with Gradle

JMFramework for Netbeans Java Application with Gradle
Use this [JMJavaFramework][JMJavaFramework] as submodule in your Gradle Java Application Project. This Framework also includes as it's submodule.

### How to use:
- Clone this repository as your Project's Submodule
- In your Project's build.gradle add dependencies:
```groovy
implementation project(path: 'JMPCFramework')
implementation project(path: 'JMPCFramework:JMJavaFramework')
```
- In your Project's settings.gradle add this at the bottom: `include ':JMPCFramework',':JMPCFramework:JMJavaFramework'`
- Download this [MSExcelFile][MSExcelFile] and put it in your project's directory: `main/resources/raw`
- In your Project's main class, add this at the constructor function:
```java
JMFunctions.setUIListener(new JMPCUIMessenger());
JMFunctions.setAsyncListener(new JMPCAsyncLoaderDefault( your_JLabel , your_JPanel ));
String languageExcelFileName = "raw/JMLanguagePack.xlsx";
ClassLoader classLoader = ClassLoader.getSystemClassLoader();
File languageExcelFile = new File(classLoader.getResource(languageExcelFileName).getFile());
JMPCFunctions.init(languageExcelFile);
```
- Create GitIgnoreDBConnection.java class in your Project:
```java
public class GitIgnoreDBConnection {
    public static JMConnection mySQLConnection(){
        return new JMConnection(new JMDBMySQL("SERVER","PORT","DATABASE","USER","PASS"));
    }
    public static JMConnection mySQLiteConnection(){
        return new JMConnection(new File("SQLITE_FILE_PATH"));
    }
}
```

##### Happy coding :smile::smile::smile:

[JMJavaFramework]: https://github.com/jmo0612/JMJavaFramework "JMJavaFramework"
[JMJavaFramework]: https://github.com/jmo0612/JMJavaFramework "JMJavaFramework"
[JMJavaFramework]: https://github.com/jmo0612/JMJavaFramework "JMJavaFramework"
[MSExcelFile]: https://github.com/jmo0612/test-netbeans/raw/master/src/main/resources/raw/JMLanguagePack.xlsx "MSExcel File"


# react-native-mta

## Getting started

`$ npm install react-native-mta --save`

### Mostly automatic installation

`$ react-native link react-native-mta`

### Manual installation


#### iOS

1. In XCode, in the project navigator, right click `Libraries` ➜ `Add Files to [your project's name]`
2. Go to `node_modules` ➜ `react-native-mta` and add `RNMta.xcodeproj`
3. In XCode, in the project navigator, select your project. Add `libRNMta.a` to your project's `Build Phases` ➜ `Link Binary With Libraries`
4. Run your project (`Cmd+R`)<

#### Android

1. Open up `android/app/src/main/java/[...]/MainActivity.java`
  - Add `import com.reactlibrary.RNMtaPackage;` to the imports at the top of the file
  - Add `new RNMtaPackage()` to the list returned by the `getPackages()` method
2. Append the following lines to `android/settings.gradle`:
  	```
  	include ':react-native-mta'
  	project(':react-native-mta').projectDir = new File(rootProject.projectDir, 	'../node_modules/react-native-mta/android')
  	```
3. Insert the following lines inside the dependencies block in `android/app/build.gradle`:
  	```
      compile project(':react-native-mta')
  	```

## Usage
```javascript
import RNMta from 'react-native-mta';

// TODO: What to do with the module?
RNMta;
```

const path = require('path')
const file = require('./file')

const currentDir = path.dirname(__filename)
const nodeModuleDir = currentDir + '/../../..'
const projectDir = currentDir + '/../../../..'

const modifies = {
  get reactSpec () {
    const avoidDupSign = '# Dependency'
    return [
      nodeModuleDir + '/react-native/React.podspec',
      avoidDupSign + '\n  s.subspec "Dependency" do |ss|\n    ss.source_files         = "React/**/*.h"\n  end\n\n  ',
      's.subspec "Core" do |ss|',
      avoidDupSign
    ]
  }
}

file.fileInsert(...modifies.reactSpec)
file.fileReplace(projectDir + '/android/settings.gradle', ':@yyyyu/react-native-mta', ':react-native-mta')
file.fileReplace(projectDir + '/android/app/build.gradle', ':@yyyyu/react-native-mta', ':react-native-mta')

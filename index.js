import { NativeModules } from 'react-native'

const { RNMta } = NativeModules

const Errs = {
  InitialFailed: {
    errCode: -1,
    errMsg: 'MTA初始化失败'
  }
}

let const checkInitialResult = async () => {
  let isInitialSucc = await RNMta.checkInitialResult()
  if (isInitialSucc === 'false') {
    throw Err.InitialFailed
  }
  checkInitialResult = () => true
  return true
}

export default RNMta

import { NativeModules } from 'react-native'

const { RNMta } = NativeModules

const Errs = {
  InitialFailed: {
    errCode: -1,
    errMsg: 'MTA初始化失败'
  }
}

let checkInitialResult = async () => {
  let isInitialSucc = await RNMta.checkInitialResult()
  if (isInitialSucc === 'false') {
    throw Err.InitialFailed
  }
  checkInitialResult = () => true
  return true
}

/**
 * 注册 MTA
 * @param  {String}  appKey
 * @param  {String}  channel
 * @param  {Boolean}  isDebug
 * @return {Promise}
 */
const startWithAppkey = async ({
  appKey,
  channel,
  isDebug = false
}) => {
  const res = await RNMta.startWithAppkey(appKey, isDebug ? 'true' : 'false', channel)
  return res === 'true'
}

export default {
  startWithAppkey
}

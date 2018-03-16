#import "RNMta.h"

@implementation RNMta

- (instancetype)init
{
    self = [super init];
    if (self) {
        _isRunSuccess = false;
    }
    return self;
}

- (dispatch_queue_t)methodQueue
{
    return dispatch_get_main_queue();
}

+ (BOOL)requiresMainQueueSetup
{
    return YES;
}

+ (NSString *)getOperateResult:(BOOL)isOperateSucc
{
    return isOperateSucc ? @"true" : @"false";
}

RCT_EXPORT_MODULE()

RCT_REMAP_METHOD(startWithAppkey,
                 startWithAppkey:(NSString *)appKey
                 isDebug:(NSString *)isDebug
                 channel:(NSString *)channel
                 resolver:(RCTPromiseResolveBlock)resolve
                 rejecter:(RCTPromiseRejectBlock)reject)
{
    if(![appKey isEqualToString:@""]) {
        [MTA startWithAppkey:appKey];
        _isRunSuccess = true;
        NSLog(@"MTA init success. appKey: %@", appKey);
    } else {
        NSLog(@"There is no appKey for MTA.");
    }
    resolve([RNMta getOperateResult:_isRunSuccess]);
}

RCT_REMAP_METHOD(checkInitialResult,
                 checkInitialResultWithResolver:(RCTPromiseResolveBlock)resolve
                 rejecter:(RCTPromiseRejectBlock)reject)
{
    resolve([RNMta getOperateResult:_isRunSuccess]);
}

@end

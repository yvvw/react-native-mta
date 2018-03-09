#import "RNMta.h"

@implementation RNMta

static bool isRunSucc = false;

- (dispatch_queue_t)methodQueue
{
    return dispatch_get_main_queue();
}

+ (void)startWithAppkey:(NSString *)appKey
{
    if([appKey length] != 0) {
        [MTA startWithAppkey:appKey];
        isRunSucc = true;
        NSLog(@"MTA init success. appKey: %@", appKey);
    } else {
        NSLog(@"There is no appKey for MTA.");
    }
}

+ (NSString *)getOperateResult:(BOOL)isOperateSucc
{
    return isOperateSucc ? @"true" : @"false";
}

RCT_EXPORT_MODULE()

RCT_REMAP_METHOD(checkInitialResult,
                 checkInitialResultWithResolver:(RCTPromiseResolveBlock)resolve
                 rejecter:(RCTPromiseRejectBlock)reject)
{
    resolve([RNMta getOperateResult:isRunSucc]);
}

@end

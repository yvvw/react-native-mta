#import <React/RCTBridgeModule.h>
#import "MTA.h"
#import "MTAConfig.h"

@interface RNMta : NSObject <RCTBridgeModule>

+ (void)startWithAppkey:(NSString *)appKey;

@end
  

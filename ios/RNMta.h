#import "MTA.h"
#import "MTAConfig.h"

#import <React/RCTBridgeModule.h>

@interface RNMta : NSObject <RCTBridgeModule>

@property (readonly) BOOL isInitSuccess;

@end

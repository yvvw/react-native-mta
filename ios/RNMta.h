#if __has_include(<React/RCTBridgeModule.h>)
#import <React/RCTBridgeModule.h>
#else
#import "RCTBridgeModule.h"
#endif

#import "MTA.h"
#import "MTAConfig.h"

@interface RNMta : NSObject <RCTBridgeModule>

@property (readonly) BOOL isInitSuccess;

@end

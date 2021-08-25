//
//  JTouchID.m
//  JTouchID
//
//  Created by Alex Suzuki on 25.08.21.
//

#import "JTouchID.h"

#import <LocalAuthentication/LocalAuthentication.h>

int32_t touchid_supported(void) {
    BOOL supportsTouchID = [[LAContext new] canEvaluatePolicy:LAPolicyDeviceOwnerAuthenticationWithBiometrics error:nil];
    NSLog(@"Supports TouchID: %d", supportsTouchID);
    return supportsTouchID;
}

void touchid_authenticate(const char* msg, authenticate_callback_type callback) {
    NSLog(@"Authenticate with biometrics");
    NSString* reason = [NSString stringWithCString:msg encoding:NSUTF8StringEncoding];
    [[LAContext new] evaluatePolicy:LAPolicyDeviceOwnerAuthenticationWithBiometrics
                    localizedReason:reason
                              reply:^(BOOL success, NSError * _Nullable error) {
        if (success) {
            NSLog(@"Successfully authenticatedw with biometrics");
            callback(YES, 0);
        } else {
            NSLog(@"Biometric authentication failed: %@", error);
            callback(NO, (int32_t)error.code);
        }
    }];
}

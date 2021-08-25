//
//  JTouchID.h
//  JTouchID
//
//  Created by Alex Suzuki on 25.08.21.
//

#import <Foundation/Foundation.h>

// Functions called from Java using JNI or JNA need to follow C calling conventions

typedef void (*auth_cb_t)(int32_t success, int32_t laError);

#ifdef __cplusplus
extern "C" {
#endif

// https://developer.apple.com/documentation/localauthentication/lacontext/1514149-canevaluatepolicy
int32_t touchid_supported(void);

// https://developer.apple.com/documentation/localauthentication/lacontext/1514176-evaluatepolicy
void touchid_authenticate(const char* msg, auth_cb_t callback);

#ifdef __cplusplus
}
#endif


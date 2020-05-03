////////////////////////////////////////////////////
 export const ACTION_reg_success = (p) => {
      return {type:'REG_SUCCESS',
            payload: p
      };
       }  
export const ACTION_reg_fail = (p) => {
         return {type:'REG_FAIL',
               payload: p
         };
          }  
///////////////////////////////////////////////////////
 export const ACTION_auth_success = (p) => {
      return {type:'AUTH_SUCCESS',
            payload: p
      };
       }  
export const ACTION_auth_fail = (p) => {
         return {type:'AUTH_FAIL',
               payload: p
         };
          }  
///////////////////////////////////////////////////////
          export const inc_counter = (p) => {
            return {type:'INC',
                  payload: p
            };
             }  
             export const dec_counter = (p) => {
                return {type:'DEC' ,payload: p
               };
                 }  
            
     
     
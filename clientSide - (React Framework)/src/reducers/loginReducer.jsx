
 const initialState={
    jwttoken:"",
       auth_state:"",
       username:"",
       password:""
}

const loginReducer=(state=initialState,action)=>{
    console.log(state.username)
    switch (action.type){
        case 'AUTH_SUCCESS': return{
            ...state, 
            auth_state:"Success",
            username: action.payload.userName
        };
        case 'AUTH_FAIL': return{
            ...state,
            auth_state:"Failed",
            username: action.payload.userName
            };
}
return state;
}

export default loginReducer;
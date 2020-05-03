
 
 const initialState={
    counter: 0,
    userdata:"mahesh"
}

const counterReducer=(state=initialState,action)=>{
    console.log(state.counter)
    switch (action.type){
        case 'INC': return{
            ...state, 
        userdata: action.payload,
        counter : state.counter+1
            
        };
        case 'DEC': return{
            ...state,
            userdata: state.ud,
            counter : state.counter-1
            };
}
return state;
}

export default counterReducer;
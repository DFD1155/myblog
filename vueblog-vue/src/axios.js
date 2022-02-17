import axios from 'axios';
import ElementUI from 'element-ui'

axios.defaults.baseURL = 'http://192.168.137.1:8001';

axios.interceptors.request.use(config => {
  return config
})

axios.interceptors.response.use(response => {
  let res = response.data
  console.log(res);

  // if (res.code === 200) {
  //   return response
  // } else {
  //   ElementUI.Message.error({ message: "登录失败!", duration: 2 * 1000 });
  //   return Promise.reject(res.message);
  // }
  if (res.message !== null) {
    if (res.code === 200) {
      ElementUI.Message.success({ message: res.message, duration: 2 * 1000 })
    } else {
      ElementUI.Message.error({ message: res.message, duration: 3 * 1000 })
    }
  }
  if (res.code === 200) {
    return response
  } else {
    return Promise.reject(res.message)
  }
}
)
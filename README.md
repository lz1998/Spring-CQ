# Spring-CQ
[![Build Status](https://travis-ci.org/lz1998/spring-cq.png)](https://travis-ci.org/lz1998/spring-cq)
## 编写插件
1. 在plugin下写XXXPlugin，继承CQPlugin
2. 注解@Component加入容器
3. 注解@Order表示插件执行顺序
## 运行
1. 安装酷Q和CQHTTP插件，推荐使用docker
    ```shell
    docker run -d --name cq01 \
    -v $(pwd)/coolq:/home/user/coolq \
    -p 9000:9000 \
    -e VNC_PASSWD=你的VNC密码(不超过8位) \
    -e COOLQ_URL=http://dlsec.cqp.me/cqa-tuling \
    -e COOLQ_ACCOUNT=你的QQ号 \
    -e CQHTTP_USE_HTTP=false \
    -e CQHTTP_USE_WS_REVERSE=true \
    -e CQHTTP_WS_REVERSE_API_URL=ws://127.0.0.1:8081/ws/api/ \
    -e CQHTTP_WS_REVERSE_EVENT_URL=ws://127.0.0.1:8081/ws/event/ \
    richardchien/cqhttp
    ```
2. 修改application.properties的端口和WebSocketConfig的路径
3. 运行WsApplication
4. 如果有问题可以访问 http://127.0.0.1:9000 使用noVNC查看酷Q日志

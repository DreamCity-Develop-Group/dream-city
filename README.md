# dream-city.
dream-city game repo


				
注册中心	city-server		8888	9999
				
				
服务管理	city-admin		8080	8081
				
				
用户服务	city-user		8060	8061
				
				
信息服务	city-message		8050	8051
				
				
数据交互服务	city-comm		8010	8011

消息负载配置：
map $http_upgrade $connection_upgrade {
        default upgrade;
        '' close;
    }
    upstream websocket {
        #hash $remote_addr consistent;
        server localhost:8010;
        server localhost:8011;
        server localhost:8012;
    }

    server {
        ###SiteName  socket
        listen       *:20088;
        server_name  localhost;
        root         "C:/Programs/Visual-NMP-x64/www/Apps";
        #error_log    "C:/Programs/Visual-NMP-x64/logs/Nginx/socket-error.log";
        #access_log   "C:/Programs/Visual-NMP-x64/logs/Nginx/socket-access.log";
        autoindex    on;
        index        index.php index.html index.htm;

        location  ~ [^/]\.php(/|$) {
                fastcgi_split_path_info  ^(.+?\.php)(/.*)$;
                if (!-f $document_root$fastcgi_script_name) {
                        return 404;
                }
                fastcgi_pass   127.0.0.1:9001;
                fastcgi_index  index.php;
                fastcgi_param  SCRIPT_FILENAME  $document_root$fastcgi_script_name;
                fastcgi_param  PATH_INFO        $fastcgi_path_info;
                fastcgi_param  PATH_TRANSLATED  $document_root$fastcgi_path_info;
                include        fastcgi_params;
        }
        
        location / {
            proxy_pass http://websocket;
            proxy_read_timeout 300s;
            proxy_set_header Host $host;
            proxy_set_header X-Real-IP $remote_addr;
            proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
            proxy_http_version 1.1;
            proxy_set_header Upgrade $http_upgrade;
            proxy_set_header Connection $connection_upgrade;
        }
        
    }

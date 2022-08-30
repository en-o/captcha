# captcha
验证码服务

# 功能解释
1. 验证码功能脱离业务主逻辑
2. 解耦业务后方便为验证码功能做扩展

# 接口列表    
1. 获取验证码    
    1.1 根据风险等级获取不同的验证码（前端传入风险等级）        
2. 验证验证码    
    2.1 根据用户信息查询需要验证和验证验证码正确性（不需要验证则直接放，需要验证则验证是否正确）    
3. 风险等级接口获取    
    3.1 不同风险等级使用的验证码不一样    
4. 新增用户登录风险    
    4.1 用户登录后为用户新增风险等级    
    4.2 等级设置（等级越高，越验证轻松）    
    4.3 后端没调用一次则+1，到阈值之后升入高等级列表    

# 业务组件
1. spring web    
2. redis    
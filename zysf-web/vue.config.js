const webpack = require('webpack')
//分析插件
const BundleAnalyzerPlugin = require('webpack-bundle-analyzer').BundleAnalyzerPlugin;
//压缩插件
const CompressionPlugin = require("compression-webpack-plugin")
module.exports = {
    chainWebpack:  config => {
        // ie报错无效字符 添加该配置项 解决该问题
        config.module
            .rule('iview')
            .test(/iview.src.*?js$/)
            .use('babel')
            .loader('babel-loader')
            .end()
    },
    configureWebpack: config => {
        let plugins = config.plugins, args = process.argv;
        plugins = plugins? plugins:[];
        if (args != null && args.length > 0 && args.indexOf('--report') != -1) {
            plugins.push(
                new BundleAnalyzerPlugin({
                    generateStatsFile:true
                })
            )
        }
        if(process.env.NODE_ENV === 'production'){
            plugins.push(
                new CompressionPlugin({
                    test:/\.(js|css|svg|woff|ttf|json|html)/, //匹配文件名
                    threshold: 10240,//对超过10k的数据压缩
                    deleteOriginalAssets: false //不删除源文件
                })
            );
        }
    },
    chainWebpack:  config => {
        // ie报错无效字符 添加该配置项 解决该问题
        config.module
            .rule('iview')
            .test(/iview.src.*?js$/)
            .use('babel')
            .loader('babel-loader')
            .end()
    },
    transpileDependencies: []       // 需要编译的依赖包名
}

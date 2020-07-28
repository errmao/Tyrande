const path = require('path');
const {
    config
} = require('process');

module.exports = {
    // 基本路径
    publicPath: process.env.NODE_ENV === 'production' ? '' : './',
    // 输出文件目录
    outputDir: process.env.NODE_ENV === 'production' ? 'dist' : 'devdist',
    // eslint-loader 是否在保存时检查
    lintOnSave: false,

    // webpack 相关
    chainWebpack: (config) => {
        const svgRule = config.module.rule('svg');
        svgRule.uses.clear();
        svgRule
            .use('svg-sprite-loader')
            .loader('svg-sprite-loader')
            .options({
                symbolId: 'icon-[name]',
                include: ['./src/icons']
            });
    },
    configureWebpack: (config) => {
        // 配置解析別名
        config.resolve = {
            // 自动添加文件名后缀
            extensions: ['.js', '.json', '.vue'],
            alias: {
                '@': path.resolve(__dirname, './src'),
                '@c': path.resolve(__dirname, './src/components')
            }
        }
    },
    // 生产环境是否生成 sourceMap 文件
    productionSourceMap: false,
    // css 相关配置
    css: {
        // 是否使用 css 分离插件 ExtractTextPlugin
        extract: true,
        // 开启 css source maps
        sourceMap: false,
        // css 预设器配置项
        loaderOptions: {
            scss: {
                // 清除浏览器的默认样式
                prependData: `@import './src/style/main.scss';`
            }
        }
    },
    parallel: require('os').cpus.length > 1,

    /**
     * pwa 插件相关配置
     */
    pwa: {},

    /**
     * webpack-dev-server 相关配置
     */
    // devServer: {
    //   // 编译完成是否打开网页
    //   open: false,
    //   // 指定使用地址, 默认 localhost, 0.0.0.0 表示可以被外界访问
    //   host: '0.0.0.0',
    //   port: 8080,
    //   // 编译失败时刷新页面
    //   https: false,
    //   // 开启热加载
    //   hot: true,
    //   hotOnly: false,
    //   proxy: {
    //     [process.env.VUE_APP_API]: {
    //       // API 服务器地址
    //       target: process.env.VUE_API_DEV_TARGET,
    //       changeOrigin: true,
    //       pathRewrite: {
    //         [`^${process.env.VUE_APP_API}`]: ''
    //       }
    //     }
    //   }
    // }
}
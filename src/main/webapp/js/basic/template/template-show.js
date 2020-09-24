/**
 * 模板维护页JS
 * User: zhaoming
 * Date: 13-4-2
 * Time: 下午5:23
 * To change this template use File | Settings | File Templates.
 */
$(function(){
    SyntaxHighlighter.config.clipboardSwf = '/static/js/syntaxhighlighter/scripts/clipboard.swf';
    SyntaxHighlighter.config.strings = {
        expandSource : '展开代码',
        viewSource : '查看代码',
        copyToClipboard : '复制代码',
        copyToClipboardConfirmation : '代码复制成功',
        print : '打印',
        help: '?',
        alert: '\n\n',
        noBrush: '不能找到刷子: ',
        brushNotHtmlScript: '刷子没有配置html-script选项',
        aboutDialog: '<div></div>'

    };
    SyntaxHighlighter.all();
});


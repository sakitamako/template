<!-- このファイルが通常のHTMLファイルではなく、JSPであることを示している -->
<!-- strutsタグ（下の補足参照）を使用する際に記述します。ここでは”s”としてタグを使用 -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta http-equiv="Content-Style-Type" content="text/css" />
        <meta http-equiv="Content-Script-Type" content="text/javascript" />
        <meta http-equiv="imagetoolbar" content="no" />
        <meta name="description" content="" />
        <meta name="keywords" content="" />

        <title>MyPage画面</title>

        <style type="text/css">

/*  TAG LAYOUT  */

body {
    margin: 0;
    padding: 0;
    line-height: 1.6;
    letter-spacing: 1px;
    font-family: Verdana, Helvetica, sans-serif;
    font-size: 12px;
    color: #333;
    background: #fff;
}

table {
    text-align: center;
    margin: 0 auto;
}

/*  ID LAYOUT  */

#top {
    width: 780px;
    margin: 30px auto;
    border: 1px solid #333;
}

#header {
    width: 100%;
    height: 80px;
    background- color: black;
}

#main {
    width: 100%;
    height: 500px;
    text-align: center;
}

#footer {
    width: 100%;
    height: 80px;
    background-color: black;
    clear: both;
}

        </style>
    </head>
    <body>
        <div id="header">
            <div id="pr"></div>
        </div>
        <div id="main">
            <div id="top">
                <p>MyPage</p>
            </div>
            <div>
                <s:if test="session.message == '' ">
                    <h3>ご購入情報は以下になります！</h3>
                    <table>
                        <tr>
                            <td>商品名</td>
                            <td>
                                <s:property value="session.buyItem_name" />
                            </td>
                        </tr>
                        <tr>
                            <td>値段</td>
                            <td>
                                <s:property value="session.total_price" />
                                <span>円</span>
                            </td>
                        </tr>
                        <tr>
                            <td>購入個数</td>
                            <td>
                                <s:property value="session.total_count" />
                                <span>個</span>
                            </td>
                        </tr>
                        <tr>
                            <td>支払い方法</td>
                            <td>
                                <s:property value="session.total_payment" />
                            </td>
                        </tr>
                    </table>
                    <s:form action="MyPageAction">
                        <input type="hidden" name="deleteFlg" value="1">
                        <s:submit value="削除" method="delete" />
                    </s:form>
                </s:if>
                <s:if test="session.message != null">
                    <h3><s:property value="session.message" /></h3>
                </s:if>
                <div>
                    <br>
                    <span>前画面に戻る場合は</span>
                    <a href='<s:url action="HomeAction" />'>ログアウト</a>
                    <span>をお願いします</span>
                </div>
            </div>
        </div>
        <div id="footer">
            <div id="pr"></div>
        </div>
    </body>
</html>
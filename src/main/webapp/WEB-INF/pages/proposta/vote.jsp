<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title>Proposta</title>

    <!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/roboto.min.css" rel="stylesheet">
    <link href="css/material-fullpalette.min.css" rel="stylesheet">
    <link href="css/ripples.min.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
    <style>
      * {
        box-sizing: border-box;
      }
      .header-panel {
        background-color: #009587;
        height: 144px;
        position: relative;
        z-index: 3;
      }
      .header-panel div {
        position: relative;
        height: 100%;
      }
      .header-panel h1 {
        color: #FFF;
        font-size: 20px;
        font-weight: 400;
        position: absolute;
        bottom: 10px;
        padding-left: 35px;
      }

      .menu {
        overflow: auto;
        padding: 0;
      }
      .menu, .menu * {
        -webkit-user-select: none;
        -moz-user-select: none;
        user-select: none;
      }
      .menu ul {
        padding: 0;
        margin: 7px 0;
      }
      .menu ul li {
        list-style: none;
        padding: 20px 0 20px 50px;
        font-size: 15px;
        font-weight: normal;
        cursor: pointer;
      }
      .menu ul li.active {
        background-color: #dedede;
        position: relative;
      }
      .menu ul li a {
        color: rgb(51, 51, 51);
        text-decoration: none;
      }

      .pages {
        position: absolute;
        top: 40px;
        right: 0;
        z-index: 4;
        padding: 0;
        overflow: auto;
      }
      .pages > div {
        padding: 0 5px;
        padding-top: 24px;
      }

      .pages .header {
        color: rgb(82, 101, 162);
        font-size: 24px;
        font-weight: normal;
        margin-top: 5px;
        margin-bottom: 60px;
        letter-spacing: 1.20000004768372px;
      }

      .page {
        transform: translateY(1080px);
        transition: transform 0 linear;
        display: none;
        opacity: 0;
        font-size: 16px;
      }

      .page.active {
        transform: translateY(0px);
        transition: all 0.3s ease-out;
        display: block;
        opacity: 1;
      }

      #opensource {
        color: rgba(0, 0, 0, 0.62);
        position: fixed;
        margin-top: 50px;
        margin-left: 50px;
        z-index: 100;
      }

      #source-modal h4 {
        color: black;
      }
      #button h2 {
        padding: 14px;
        margin: 0;
        font-size: 16px;
        font-weight: 400;
      }
      #radio-button h2 {
        font-size: 18.7199993133545px;
        font-weight: bold;
        margin-bottom: 30px;
        margin-top: 50px;
      }
    </style>
  </head>
  <body>

    <div class="header-panel shadow-z-2">
      <div class="container-fluid">
        <div class="row">
          <div class="col-xs-3 ">
            <h1>Project name</h1>
          </div>
          <ul class="nav nav-tabs pull-right" style="margin-bottom: 15px; border-bottom:none">
            <li class="active"><a href="#">Home</a></li>
            <li><a href="#">About</a></li>
            <li><a href="#">Contact</a></li>
          </ul>
        </div>
      </div>
    </div>

    <div class="container-fluid main">
      <div class="row">
        <div class="pages col-xs-9">
          <div class="col-xs-10">
            <div class="well page active" id="button">
              <h1 class="header">Proposta número #${proposta.id}</h1>
              <div>
                <h2>${proposta.proposta}</h2>
                <p class="btn-group-sm">
                  <s:url var="voteOK" action="vote">
                    <s:param name="hash" value="%{hash}" />
                    <s:param name="voto.voto" value="true" />
                  </s:url>
                  <s:a href="%{voteOK}" cssClass="btn btn-primary btn-flat" role="button"><span class="mdi-navigation-check" aria-hidden="true"></span> Votar a favor</s:a>

                  <s:url var="voteNo" action="vote">
                    <s:param name="hash" value="%{hash}" />
                    <s:param name="voto.voto" value="false" />
                  </s:url>
                  <s:a href="%{voteNo}" cssClass="btn btn-default btn-flat" role="button"><span class="mdi-navigation-close" aria-hidden="true"></span> Votar contra</s:a>
                </p>
              </div>

              <div class="detailBox">
                <div id="radio-button">
                  <h2> Dúvidas e esclarecimentos </h2>
                </div>

                <div class="list-group">
                  <s:iterator value="perguntas">
                    <div class="list-group-item">
                      <div class="row-content">
                        <div class="least-content"><s:date name="data" format="dd/MM/yy" /></div>
                        <p class="list-group-item-text">${pergunta}</p>
                        <div class="actionBox">
                          <ul class="commentList">
                            <s:iterator value="respostas">
                              <li>
                                <div class="commentText">
                                  <p class="">${resposta}</p>
                                  <span class="date sub-text">${data}</span>
                                </div>
                              </li>
                            </s:iterator>
                          </ul>
                        </div>
                      </div>
                    </div>
                    <div class="list-group-separator"></div>
                  </s:iterator>
                </div>
            
                <form id="form_pergunta" class="form-horizontal" role="form">
                  <s:hidden name="hash" value="%{hash}" />
                  <div class="form-group">
                    <label class="control-label sr-only">Pergunta</label>
                    <div class="col-sm-9">
                      <input class="form-control" name="pergunta.pergunta" type="text" placeholder="Sua pergunta" />
                    </div>
                    <div class="col-sm-3">
                      <button class="btn btn-default">Perguntar</button>
                    </div>
                  </div>
                </form>

              </div>
            </div>
          </div>
        </div>
      </div>
    </div>

  <footer class="footer" style="position: fixed; bottom: 0">
    <p>© Lorenz e CIA 2016</p>
  </footer>

  <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
  <script src="js/jquery-1.11.2.min.js"></script>
  <!-- Include all compiled plugins (below), or include individual files as needed -->
  <script src="js/bootstrap.min.js"></script>

  <script>
    $(document).ready( function() {
      $("#form_pergunta").on( "submit", function(event) {
        event.preventDefault();

        var params = $(this).serialize();

        $.post("perguntar", params, function(data) {
          //if success
          var dtData = new Date(data.pergunta.data);
          var strData = dtData.getDate().toString();
          strData = strData[1] ? strData : "0"+strData[0];
          var temp = (dtData.getMonth()+1).toString()
          strData += "/" + (temp[1] ? temp : "0"+temp[0]);
          temp = dtData.getFullYear().toString();
          strData += "/" + temp[2] + temp[3];

          $(".list-group").append('<div class="list-group-item"><div class="row-content"><div class="least-content">' + strData + '</div><p class="list-group-item-text">' + data.pergunta.pergunta + '</p></div></div><div class="list-group-separator"></div>');
        }, "json").fail(function() {
          //if fails
        });
      });
    });
  </script>

  </body>
</html>
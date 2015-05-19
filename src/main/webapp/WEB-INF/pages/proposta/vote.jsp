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

    <link href="css/jumbotron-narrow.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>
  <body>

    <div class="container">
      <div class="header clearfix">
        <nav>
          <ul class="nav nav-pills pull-right">
            <li role="presentation" class="active"><a href="#">Home</a></li>
            <li role="presentation"><a href="#">About</a></li>
            <li role="presentation"><a href="#">Contact</a></li>
          </ul>
        </nav>
        <h3 class="text-muted">Project name</h3>
      </div>

      <div class="jumbotron">
        <h2>Proposta número #${proposta.id}</h2>
        <p class="lead">${proposta.proposta}</p>
        <p class="btn-group-sm">
          <a class="btn btn-sm btn-primary" href="#" role="button"><span class="glyphicon glyphicon-thumbs-up" aria-hidden="true"></span> Votar a favor </a>
          <a class="btn btn-sm btn-danger" href="#" role="button"> <span class="glyphicon glyphicon-thumbs-down" aria-hidden="true"></span> Votar contra </a>
        </p>
      </div>

      <div class="detailBox">
        <div class="titleBox">
          <label> Dúvidas e esclarecimentos </label>
        </div>
        <s:iterator value="perguntas">
          <div class="commentBox">
            <p class="taskDescription">${pergunta}</p>
            <span class="date sub-text">${data}</span>
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
        </s:iterator>
        <div class="actionBox">
          <form class="form-inline" role="form">
            <div class="form-group">
              <input class="form-control" type="text" placeholder="Sua pergunta" />
            </div>
            <div class="form-group">
              <button class="btn btn-default">Perguntar</button>
            </div>
          </form>
        </div>
      </div>

      <footer class="footer">
        <p>© Lorenz e CIA 2016</p>
      </footer>

    </div>

    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="js/jquery-1.11.2.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>

  </body>
</html>
//서버로부터 데이터를 받아서 하위컴포넌트로 전달하여 사용하는 예제

var CommentForm = React.createClass({
	render: function() {
		return (
			<div className="commentForm">
				안녕! 난 댓글 폼이야.
			</div>
		);
	}
});

var Comment = React.createClass({
	render: function() {
		var rawMarkup = marked(this.props.children.toString(), {sanitize: true});
		return (
			<div className="comment">
				<h2 className="commentAuthor">
					{this.props.author}
				</h2>
				<span dangerouslySetInnerHTML={{__html: rawMarkup}} />
			</div>
		);
	}
});

var CommentList = React.createClass({
	render: function() {
		if (this.props.data) {
			var commentNodes = this.props.data.map(function (comment) {
				return (
					<Comment author={comment.author}>
						{comment.text}
					</Comment>
				);
			});
		}
		return (
			<div className="commentList">
				{commentNodes}
			</div>
		);
	}
});

var CommentBox = React.createClass({
	render: function() {
		return (
			<div className="commentBox">
				<h1>댓글</h1>
				<CommentList data={this.state.data} />
				<CommentForm />
			</div>
		);
	}
});

React.render(
	<CommentBox url="/hello/getData" />,
	document.getElementById('content')
);
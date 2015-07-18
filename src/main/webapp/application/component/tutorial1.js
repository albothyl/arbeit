//각 컴포넌트를 만들고 이를 조합하는 예제

var CommentList = React.createClass({
	render: function() {
		return (
			<div className="commentList">
				안녕! 난 댓글목록이야.
			</div>
		);
	}
});

var CommentForm = React.createClass({
	render: function() {
		return (
			<div className="commentForm">
				안녕! 난 댓글 폼이야.
			</div>
		);
	}
});

var CommentBox = React.createClass({
	render: function() {
		return (
			<div className="commentBox">
				<h1>댓글</h1>
				<CommentList />
				<CommentForm />
			</div>
		);
	}
});
React.render(
	<CommentBox />,
	document.getElementById('content')
);
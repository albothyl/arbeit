//상위 컴포넌트에서 데이터를 설정하고, 이를 하위 컴포넌트에서 사용하는 예제

var CommentList = React.createClass({
	render: function() {
		return (
			<div className="commentList">
				<Comment author="Pete Hunt">댓글입니다</Comment>
				<Comment author="Jordan Walke">*또 다른* 댓글입니다</Comment>
			</div>
		);
	}
});

var Comment = React.createClass({
	render: function() {
		return (
			<div className="comment">
				<h2 className="commentAuthor">
					{this.props.author}
				</h2>
				{this.props.children}
			</div>
		);
	}
});

React.render(
	<CommentList />,
	document.getElementById('content')
);
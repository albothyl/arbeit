/**
 * Created by Administrator on 2015-07-04.
 */
[
	{"author": "Pete Hunt", "text": "댓글입니다"},
	{"author": "Jordan Walke", "text": "*또 다른* 댓글입니다"}
]

var CommentBox = React.createClass({
	getInitialState: function() {
		return {data: []};
	},
	componentDidMount: function() {
		$.ajax({
			url: this.props.url,
			dataType: 'json',
			cache: false,
			success: function(data) {
				this.setState({data: data});
			}.bind(this),
			error: function(xhr, status, err) {
				console.error(this.props.url, status, err.toString());
			}.bind(this)
		});
	},
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

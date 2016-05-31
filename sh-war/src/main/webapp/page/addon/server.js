function Server(type_one, type_two) {
	this.init = function(one, two) {
		var _this = this;
		$("#" + type_one).change(function() {
					_this.loadServer($(this).val(), type_two);
				});
		if (one && one > 0) {
			_this.loadServer(0, type_one, one);
		} else {
			_this.loadServer(0, type_one);
		}
		if ((two && two > 0) || (one && one > 0)) {
			_this.loadServer(one, type_two, two);
		} else {
			_this.cleanOptions(type_two);
		}

	};

	this.loadServer = function(serverId, two, sid) {
		var _this = this;
		if (serverId == 0 && two == type_two) {
			_this.cleanOptions(type_two);
		} else {
			$.ajax({
						url : ctx + "/page/storeServer/getServer.s",
						cache : false,
						ifModified : true,
						data : {
							serverId : serverId,
							t : Math.random()
						},
						success : function(data) {
							if (data.success) {
								var server = data.server;
								_this.addOptions(server, two);
								if (sid && sid > 0) {
									$("#" + two).val(sid);
								}
							}
						},
						error : function() {
						}
					});
		}

	};
	this.addOptions = function(server, two) {
		this.cleanOptions(two);
		for (var index = 0; index < server.length; index++) {
			$("#" + two).append("<option value='" + server[index].categoryId + "'>"
					+ server[index].categoryDesc + "</option>");
		}
	};

	this.cleanOptions = function(two) {
		$("#" + two).empty();
		$("#" + two).append("<option value='0'>全部服务</option>");
	};

}
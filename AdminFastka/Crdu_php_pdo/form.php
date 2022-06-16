<!-- add/edit form modal -->
<div class="modal fade" id="userModal" tabindex="-1" role="dialog" aria-labelledby="userModalLabel"
  aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Add/Edit User <i class="fa fa-user-circle-o"
            aria-hidden="true"></i></h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">×</span>
        </button>
      </div>
      <form id="addform" method="POST" enctype="multipart/form-data">
        <div class="modal-body">
          <div class="form-group">
            <label for="recipient-name" class="col-form-label">Name:</label>
            <div class="input-group mb-3">
              <div class="input-group-prepend">
                <span class="input-group-text" id="basic-addon1"><i class="fa fa-user-circle-o"
                    aria-hidden="true"></i>
              </div>
              <input type="text" class="form-control" id="title" name="title" required="required">
            </div>
          </div>
          <div class="form-group">
            <label for="message-text" class="col-form-label">Description:</label>
            <div class="input-group mb-3">
              <div class="input-group-prepend">
                <span class="input-group-text" id="basic-addon1"><i class="fa fa-envelope-o"
                    aria-hidden="true"></i></span>
              </div>
              <input type="texe" class="form-control" id="description" name="description" required="required">
            </div>
          </div>
          <div class="form-group">
            <label for="message-text" class="col-form-label">Price:</label>
            <div class="input-group mb-3">
              <div class="input-group-prepend">
                <span class="input-group-text" id="basic-addon1"><i class="fa fa-phone"
                    aria-hidden="true"></i></span>
              </div>
              <input type="number" class="form-control" id="price" name="price" required="required" maxLength="10"
                minLength="1">
            </div>
          </div>
          <div class="form-group">
            <label for="message-text" class="col-form-label">Rating:</label>
            <div class="input-group mb-3">
              <div class="input-group-prepend">
                <span class="input-group-text" id="basic-addon1"><i class="fa fa-phone"
                    aria-hidden="true"></i></span>
              </div>
              <input type="float" class="form-control" id="rating" name="rating" required="required" maxLength="3"
                minLength="1">
            </div>
          </div>
          <div class="form-group">
            <label for="message-text" class="col-form-label">timeDelivery:</label>
            <div class="input-group mb-3">
              <div class="input-group-prepend">
                <span class="input-group-text" id="basic-addon1"><i class="fa fa-phone"
                    aria-hidden="true"></i></span>
              </div>
              <input type="number" class="form-control" id="timeDelivery" name="timeDelivery" required="required" maxLength="2"
                minLength="0">
            </div>
          </div>
          <div class="form-group">
            <label for="message-text" class="col-form-label">picture:</label>
            <div class="input-group mb-3">
              <div class="input-group-prepend">
                <span class="input-group-text" id="inputGroupFileAddon01"><i class="fa fa-picture-o"
                    aria-hidden="true"></i></span>
              </div>
              <div class="custom-file">
                <input type="file" class="custom-file-input" name="picture" id="picture">
                <label class="custom-file-label" for="userphoto">Choose file</label>
              </div>
            </div>

          </div>

        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
          <button type="submit" class="btn btn-success" id="addButton">Submit</button>
          <input type="hidden" name="action" value="adduser">
          <input type="hidden" name="userid" id="userid" value="">
        </div>
      </form>
    </div>
  </div>
</div>
<!-- add/edit form modal end -->
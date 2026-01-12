// 파일 등록
async function uploadToServer(formObj)
{
    const response = await axios({
                              method : 'post'
                            , url : `/upload`
                            , data: formObj
                            , header: {'Content-Type': 'multipart/form-data'}
                        });

    console.log(response.data);
    return response.data;
}

// 파일 삭제
async function removeFileToServer(uuid, fileName)
{
    const response = await axios.delete(`/remove/${uuid}_${fileName}`);
    return response.data;
}
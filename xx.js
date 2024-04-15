const fs = require('fs');
const path = require('path');

// 创建文件 xxx.txt

fs.writeFile(path.join(__dirname, 'xxx.txt'), 'hello world', (err) => {
  if (err) {
    console.log(err);
    return;
  }
  console.log('文件创建成功');
});
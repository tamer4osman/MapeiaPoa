var gulp = require('gulp');
var concat = require('gulp-concat');
var uglify = require('gulp-uglify');
var rename = require('gulp-rename');

var src = [
  'app/**/model.js',
  'app/**/*.component.js',
  'app/**/view.js'
];

gulp.task('uglify', function () {
  return gulp.src(src)
    .pipe(concat('app.js'))
    .pipe(rename({suffix: '.min'}))
    .pipe(uglify())
    .pipe(gulp.dest('dist'));
});

var gulp = require('gulp');
var amdOptimize = require('amd-optimize');
var concat = require('gulp-concat');
var uglify = require('gulp-uglify');
var rename = require('gulp-rename');

var src = [
  'src/main/resources/static/app/**/*.model.js',
  'src/main/resources/static/app/**/*.controller.js',
  'src/main/resources/static/app/**/*.view.js',
];

gulp.task('scripts', function ()
{
  return gulp.src(src)
    .pipe(concat('app.js'))
    .pipe(rename({suffix: '.min'}))
    .pipe(uglify())
    .pipe(gulp.dest('src/main/resources/static/assets'));
});

gulp.task('default', ['scripts']);
